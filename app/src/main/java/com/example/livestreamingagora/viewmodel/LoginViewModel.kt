package com.example.livestreamingagora.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.livestreamingagora.models.LoginBody
import com.example.livestreamingagora.repository.LoginRepository
import com.example.livestreamingagora.repository.LoginRepository.LoginCallBack

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: LoginRepository

    init {
        repository = LoginRepository(application)
    }

    fun userLogin(body: LoginBody?, callBack: LoginCallBack?) {
        callBack?.let {
            repository.userLogin(it, body) }
    }

    fun userLogout(callBack: LoginCallBack?) {
        if (callBack != null) {
            repository.userLogout(callBack)
        }
    }

    fun userDeActiveUser(callBack: LoginCallBack?, channelName: String?) {
        callBack?.let {
            repository.deActiveUser(it, channelName) }
    }
}
