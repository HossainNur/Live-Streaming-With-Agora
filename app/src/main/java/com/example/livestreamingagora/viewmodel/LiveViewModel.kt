package com.example.livestreamingagora.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.livestreamingagora.models.LiveBody
import com.example.livestreamingagora.repository.LiveRepository
import com.example.livestreamingagora.repository.LiveRepository.LiveCallBack

class LiveViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: LiveRepository

    init {
        repository = LiveRepository(application)
    }

    fun userLive(body: LiveBody?, callBack: LiveCallBack?) {
        if (callBack != null) {
            repository.userLive(callBack, body)
        }
    }
}
