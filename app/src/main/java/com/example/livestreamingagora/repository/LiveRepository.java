package com.example.livestreamingagora.repository;

import android.app.Application;
import android.util.Log;

import com.example.livestreamingagora.NetworkUtil;
import com.example.livestreamingagora.models.LiveBody;
import com.example.livestreamingagora.models.LiveResponse;
import com.example.livestreamingagora.models.LoginResponse;
import com.example.livestreamingagora.models.LogoutResponse;
import com.example.livestreamingagora.network.Api;
import com.example.livestreamingagora.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveRepository {

    private ApiService apiService;
    private Application application;
    private NetworkUtil networkUtil;

    public LiveRepository(Application application) {
        this.application = application;
        apiService = Api.getInstance().getApiService();
        networkUtil = NetworkUtil.getInstance(application);
    }

    public void userLive(LiveCallBack callBack, LiveBody body){
        if (!networkUtil.isNetworkAvailable()) {
            callBack.onFailure("No internet connection available");
            return;
        }
        Call<LiveResponse> call = apiService.userLive(body);
        call.enqueue(new Callback<LiveResponse>() {
            @Override
            public void onResponse(Call<LiveResponse> call, Response<LiveResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    callBack.onResponse(response.body());
                }else {
                    callBack.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<LiveResponse> call, Throwable t) {
                callBack.onFailure(t.getMessage());
            }
        });
    }

    public interface LiveCallBack{
        void onResponse(LiveResponse response);
        void onFailure(String message);
    }

}
