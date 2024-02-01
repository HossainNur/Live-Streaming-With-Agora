package com.example.livestreamingagora.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.livestreamingagora.ConstantsKt;
import com.example.livestreamingagora.repository.LoginRepository;
import com.example.livestreamingagora.models.LoginBody;

public class LoginViewModel extends AndroidViewModel {
    private LoginRepository repository;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = new LoginRepository(application);
    }

    public void userLogin(LoginBody body, LoginRepository.LoginCallBack callBack){
        repository.userLogin(callBack, body);
    }

    public void userLogout( LoginRepository.LoginCallBack callBack){
        repository.userLogout(callBack);
    }

    public void userDeActiveUser(LoginRepository.LoginCallBack callBack,String channelName){
        repository.deActiveUser(callBack,channelName);
    }
}
