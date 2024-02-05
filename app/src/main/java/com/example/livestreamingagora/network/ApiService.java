package com.example.livestreamingagora.network;

import com.example.livestreamingagora.models.DeActiveResponse;
import com.example.livestreamingagora.models.LiveBody;
import com.example.livestreamingagora.models.LiveResponse;
import com.example.livestreamingagora.models.LoginBody;
import com.example.livestreamingagora.models.LoginResponse;
import com.example.livestreamingagora.models.LogoutResponse;
import com.example.livestreamingagora.models.notification.NotificationRequest;
import com.example.livestreamingagora.models.notification.NotificationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiService {

   @POST("live-streaming-login")
   Call<LoginResponse> userLogin(@Body LoginBody body);
   @POST("logout")
   Call<LogoutResponse> userLogout(
           @Header("Authorization") String auth
   );

   @POST("store-new-live-streaming")
   Call<LiveResponse> userLive(@Body LiveBody body);

   @POST("update-steaming/{channelName}")
   Call<DeActiveResponse> deActiveUser(@Path("channelName") String channelName);

   @POST("fcm/send")
   Call<NotificationResponse> sendNotification (
           @Header("Authorization") String auth,
           @Body NotificationRequest request);


}
