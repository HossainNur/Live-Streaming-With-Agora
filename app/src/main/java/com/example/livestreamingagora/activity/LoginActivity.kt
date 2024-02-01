package com.example.livestreamingagora.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.livestreamingagora.ACCESS_TOKEN
import com.example.livestreamingagora.AGORA_TOKEN
import com.example.livestreamingagora.CHANNEL_NAME
import com.example.livestreamingagora.appCertificate
import com.example.livestreamingagora.appId
import com.example.livestreamingagora.databinding.ActivityLoginBinding
import com.example.livestreamingagora.media.RtcTokenBuilder2
import com.example.livestreamingagora.models.DeActiveResponse
import com.example.livestreamingagora.models.LoginBody
import com.example.livestreamingagora.models.LoginResponse
import com.example.livestreamingagora.models.LogoutResponse
import com.example.livestreamingagora.repository.LoginRepository.LoginCallBack
import com.example.livestreamingagora.setEditor
import com.example.livestreamingagora.uid
import com.example.livestreamingagora.viewmodel.LoginViewModel


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel : LoginViewModel
    private var token : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.loginBtn.setOnClickListener {

            val phone = binding.phoneEdtx.text.toString().trim()
            val password = binding.passwordEdtx.text.toString().trim()

            if (phone.isEmpty()) {
                binding.phoneEdtx.setError("phone number required")
                binding.phoneEdtx.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                binding.passwordEdtx.setError("password required")
                binding.passwordEdtx.requestFocus()
                return@setOnClickListener
            }

            if (!password.isNullOrEmpty() && !phone.isNullOrEmpty()){
                val body = LoginBody(phone,password)
                verifyUser(body)

            }

        }
    }


    private fun verifyUser(body: LoginBody) {
        viewModel.userLogin(body, object : LoginCallBack {
            override fun onResponse(response: LoginResponse) {
                setEditor(applicationContext, ACCESS_TOKEN,response.data.getAccessToken())
                Toast.makeText(applicationContext, response.message, Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext,LiveActivity::class.java))
                finish()
            }

            override fun onLogoutResponse(response: LogoutResponse?) {
                TODO("Not yet implemented")
            }

            override fun onDeActiveResponse(response: DeActiveResponse?) {
                TODO("Not yet implemented")
            }

            override fun onFailure(message: String) {
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            }
        })

    }

}