package com.example.livestreamingagora.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.livestreamingagora.models.notification.NotificationRequest
import com.example.livestreamingagora.repository.NotificationRepository

class NotificationViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NotificationRepository
    init {
        repository = NotificationRepository()
    }

    fun sendNotification(callback: NotificationRepository.NotificationCallBack, body: NotificationRequest){
        repository.sendNotification(callback, body)
    }
}