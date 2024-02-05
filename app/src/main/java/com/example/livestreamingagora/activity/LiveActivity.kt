package com.example.livestreamingagora.activity

import android.app.Dialog
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.livestreamingagora.ACCESS_TOKEN
import com.example.livestreamingagora.AGORA_TOKEN
import com.example.livestreamingagora.CHANNEL_NAME
import com.example.livestreamingagora.R
import com.example.livestreamingagora.TITLE
import com.example.livestreamingagora.appCertificate
import com.example.livestreamingagora.appId
import com.example.livestreamingagora.databinding.ActivityLiveBinding
import com.example.livestreamingagora.getSharedPref
import com.example.livestreamingagora.media.RtcTokenBuilder2
import com.example.livestreamingagora.models.DeActiveResponse
import com.example.livestreamingagora.models.LoginResponse
import com.example.livestreamingagora.models.LogoutResponse
import com.example.livestreamingagora.repository.LoginRepository.LoginCallBack
import com.example.livestreamingagora.setEditor
import com.example.livestreamingagora.uid
import com.example.livestreamingagora.viewmodel.LiveViewModel
import com.example.livestreamingagora.viewmodel.LoginViewModel

class LiveActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLiveBinding
    private lateinit var viewModel : LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        val token = getSharedPref(applicationContext, ACCESS_TOKEN)
        val agoraToken = getSharedPref(applicationContext, AGORA_TOKEN)


        binding.goLiveBtn.setOnClickListener {
            generateToken()
            startActivity(Intent(applicationContext,MainActivity::class.java))
        }

        binding.logutBtn.setOnClickListener {


            if (token != null){

                viewModel.userLogout( object : LoginCallBack {

                    override fun onResponse(response: LoginResponse?) {
                        TODO("Not yet implemented")
                    }

                    override fun onLogoutResponse(response: LogoutResponse?) {
                        Toast.makeText(applicationContext, response!!.message, Toast.LENGTH_SHORT).show()
                        setEditor(applicationContext, ACCESS_TOKEN,null)
                        startActivity(Intent(applicationContext,LoginActivity::class.java))
                        finish()
                    }

                    override fun onDeActiveResponse(response: DeActiveResponse?) {
                        TODO("Not yet implemented")
                    }

                    override fun onFailure(message: String?) {
                        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                    }

                })
            }

        }

    }

    private fun generateToken() {

        val tokenBuilder = RtcTokenBuilder2()
        // Calculate the time expiry timestamp
        // Calculate the time expiry timestamp
        val timestamp = (System.currentTimeMillis() / 1000 + 60).toInt()
        val result: String = tokenBuilder.buildTokenWithUid(
            appId, appCertificate,
            CHANNEL_NAME, uid, RtcTokenBuilder2.Role.ROLE_PUBLISHER, timestamp, timestamp
        )

        setEditor(applicationContext, AGORA_TOKEN,result)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        finish()
        return super.onOptionsItemSelected(item)
    }

}