package com.example.livestreamingagora.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.livestreamingagora.models.LiveBody;
import com.example.livestreamingagora.models.LoginBody;
import com.example.livestreamingagora.repository.LiveRepository;
import com.example.livestreamingagora.repository.LoginRepository;

public class LiveViewModel extends AndroidViewModel {
    private LiveRepository repository;

    public LiveViewModel(@NonNull Application application) {
        super(application);
        repository = new LiveRepository(application);
    }

    public void userLive(LiveBody body, LiveRepository.LiveCallBack callBack){
        repository.userLive(callBack, body);
    }
}
