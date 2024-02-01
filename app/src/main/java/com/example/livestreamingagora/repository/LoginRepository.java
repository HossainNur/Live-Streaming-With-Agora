package com.example.livestreamingagora.repository;
import android.app.Application;
import android.util.Log;
import com.example.livestreamingagora.ConstantsKt;
import com.example.livestreamingagora.models.DeActiveResponse;
import com.example.livestreamingagora.models.LogoutResponse;
import com.example.livestreamingagora.network.Api;
import com.example.livestreamingagora.models.LoginBody;
import com.example.livestreamingagora.models.LoginResponse;
import com.example.livestreamingagora.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private final String TAG = "LoginRepository";

    private ApiService apiService;
    private Application application;
    public LoginRepository(Application application) {
        this.application = application;
        apiService = Api.getInstance().getApiService();
    }



    public void userLogin(LoginCallBack callBack, LoginBody body){
        Call<LoginResponse> call = apiService.userLogin(body);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    Log.v(TAG, response.body().toString());
                    callBack.onResponse(response.body());
                }else {
                    Log.v(TAG, response.message());
                    callBack.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.v(TAG, "onFailure: "+t.getMessage());
                callBack.onFailure(t.getMessage());
            }
        });
    }

    public void userLogout(LoginCallBack callBack){
        String token = "Bearer "+ ConstantsKt.getSharedPref(application,ConstantsKt.ACCESS_TOKEN);
        Call<LogoutResponse> call = apiService.userLogout(token);
        call.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    Log.v(TAG, response.body().toString());
                    callBack.onLogoutResponse(response.body());
                }else {
                    Log.v(TAG, response.message());
                    callBack.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                callBack.onFailure(t.getMessage());
            }
        });
    }

    public void deActiveUser(LoginCallBack callBack,String channelName){
        Call<DeActiveResponse> call = apiService.deActiveUser(channelName);
        call.enqueue(new Callback<DeActiveResponse>() {
            @Override
            public void onResponse(Call<DeActiveResponse> call, Response<DeActiveResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    Log.v(TAG, response.body().toString());
                    callBack.onDeActiveResponse(response.body());
                }else {
                    Log.v(TAG, response.message());
                    callBack.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<DeActiveResponse> call, Throwable t) {
                callBack.onFailure(t.getMessage());
            }
        });
    }

    public interface LoginCallBack{
        void onResponse(LoginResponse response);
        void onLogoutResponse(LogoutResponse response);
        void onDeActiveResponse(DeActiveResponse response);
        void onFailure(String message);
    }
}
