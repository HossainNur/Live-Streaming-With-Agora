package com.example.livestreamingagora.activity


import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.livestreamingagora.AGORA_TOKEN
import com.example.livestreamingagora.CHANNEL_NAME
import com.example.livestreamingagora.R
import com.example.livestreamingagora.appId
import com.example.livestreamingagora.databinding.ActivityVideoBinding
import com.example.livestreamingagora.getSharedPref
import com.example.livestreamingagora.models.Data
import com.example.livestreamingagora.models.DeActiveResponse
import com.example.livestreamingagora.models.LiveBody
import com.example.livestreamingagora.models.LiveResponse
import com.example.livestreamingagora.models.LoginResponse
import com.example.livestreamingagora.models.LogoutResponse
import com.example.livestreamingagora.models.notification.NotificationRequest
import com.example.livestreamingagora.models.notification.NotificationResponse
import com.example.livestreamingagora.repository.LiveRepository
import com.example.livestreamingagora.repository.LoginRepository
import com.example.livestreamingagora.repository.NotificationRepository
import com.example.livestreamingagora.viewmodel.LiveViewModel
import com.example.livestreamingagora.viewmodel.LoginViewModel
import com.example.livestreamingagora.viewmodel.NotificationViewModel
import io.agora.rtc2.Constants
import io.agora.rtc2.IRtcEngineEventHandler
import io.agora.rtc2.RtcEngine
import io.agora.rtc2.video.VideoCanvas

class VideoActivity : AppCompatActivity() {

    private var mRtcEngine: RtcEngine? = null
    private var userRole = 1
    private var title : String? = null
    private var description : String? = null
    private var token : String? = null
    private lateinit var binding : ActivityVideoBinding
    private lateinit var viewModel :LiveViewModel
    private lateinit var notificationViewModel :NotificationViewModel
    private lateinit var loginViewModel :LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        token = getSharedPref(applicationContext, AGORA_TOKEN)
        viewModel = ViewModelProvider(this)[LiveViewModel::class.java]
        notificationViewModel = ViewModelProvider(this)[NotificationViewModel::class.java]
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        userRole = intent.getIntExtra("UserRole", -1)
        title = intent.getStringExtra("title")
        description = intent.getStringExtra("description")
        initAgoraEngineAndJoinChannel()

        if (!appId.isNullOrEmpty() && !title.isNullOrEmpty() && !description.isNullOrEmpty() && !token.isNullOrEmpty()){
            val body = LiveBody(appId,title,description,CHANNEL_NAME,token)
            verifyLiveUser(body)

        }
    }



    private fun verifyLiveUser(body: LiveBody) {
        viewModel.userLive(body, object : LiveRepository.LiveCallBack {
            override fun onResponse(response: LiveResponse?) {
                Toast.makeText(applicationContext, response?.message ?: null, Toast.LENGTH_SHORT).show()
                //sendNotification()

            }
            override fun onFailure(message: String) {
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            }
        })

    }
    private fun sendNotification() {
        notificationViewModel.sendNotification(object : NotificationRepository.NotificationCallBack{
            override fun onResponse(response: NotificationResponse?) {
                Toast.makeText(applicationContext, "notification ", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(message: String?) {
                Toast.makeText(applicationContext, "failed", Toast.LENGTH_SHORT).show()
            }
        }, NotificationRequest(
            com.example.livestreamingagora.models.notification.Data(
                description,
                title
            ),"/topics/weather"
        )
        )
    }


    override fun onDestroy() {
        super.onDestroy()
        mRtcEngine!!.leaveChannel()
        RtcEngine.destroy()
        mRtcEngine = null
        endCall(CHANNEL_NAME)
    }

    private fun initAgoraEngineAndJoinChannel() {
        initializeAgoraEngine()

        mRtcEngine!!.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING)
        mRtcEngine!!.setClientRole(userRole)

        mRtcEngine!!.enableVideo()
        if (userRole == 1)
            setupLocalVideo()
        else {
            val localVideoCanvas = findViewById<View>(R.id.local_video_view_container)
            localVideoCanvas.isVisible = false
        }

        joinChannel()
    }

    private val mRtcEventHandler: IRtcEngineEventHandler = object : IRtcEngineEventHandler() {
        override fun onUserJoined(uid: Int, elapsed: Int) {
            runOnUiThread { setupRemoteVideo(uid) }
        }

        override fun onUserOffline(uid: Int, reason: Int) {
            runOnUiThread { onRemoteUserLeft() }
        }

        override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
            runOnUiThread { println("Join channel success: $uid") }
        }
    }

    private fun initializeAgoraEngine() {
        try {
            mRtcEngine = RtcEngine.create(baseContext, appId, mRtcEventHandler)
        } catch (e: Exception) {
            println("Exception: ${e.message}")
        }
    }


    fun onSwitchCameraClicked(view: View) {
        mRtcEngine!!.switchCamera()
    }

    fun onEndCalledClicked(view: View) {
        endCall(CHANNEL_NAME)
    }

    private fun endCall(channelName: String) {

        loginViewModel.userDeActiveUser(object : LoginRepository.LoginCallBack {
            override fun onResponse(response: LoginResponse?) {

            }

            override fun onLogoutResponse(response: LogoutResponse?) {
                TODO("Not yet implemented")
            }

            override fun onDeActiveResponse(response: DeActiveResponse?) {
                Toast.makeText(applicationContext, response?.message ?: null, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(message: String) {
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            }
        },channelName)

        finish()

    }


    fun onLocalAudioMuteClicked(view: View) {
        val iv = view as ImageView
        if (iv.isSelected){
            iv.isSelected = false
            iv.clearColorFilter()
        }else {
            iv.isSelected = true
            iv.setColorFilter(resources.getColor(R.color.purple_200), PorterDuff.Mode.MULTIPLY)
        }

        mRtcEngine!!.muteLocalAudioStream(iv.isSelected)
    }
    private fun setupLocalVideo(){
        val container = findViewById<View>(R.id.local_video_view_container) as FrameLayout
        val surfaceView = RtcEngine.CreateRendererView(baseContext)
        surfaceView.setZOrderMediaOverlay(true)
        container.addView(surfaceView)
        mRtcEngine!!.setupLocalVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_FIT, 0))
    }
    private fun joinChannel(){
        mRtcEngine!!.joinChannel(token, CHANNEL_NAME, null, 0)
    }
    private fun setupRemoteVideo(uid: Int){
        val container = findViewById<View>(R.id.remote_video_view_container) as FrameLayout
        if (container.childCount >= 1) return
        val surfaceView = RtcEngine.CreateRendererView(baseContext)
        container.addView(surfaceView)
        mRtcEngine!!.setupRemoteVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_FIT, uid))
        surfaceView.tag = uid
    }
    private fun onRemoteUserLeft(){
        val container = findViewById<View>(R.id.remote_video_view_container) as FrameLayout
        container.removeAllViews()
    }
}