package com.example.livestreamingagora.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiveData {
    @SerializedName("app_id")
    @Expose
    public String appId;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("channel_name")
    @Expose
    public String channelName;
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("id")
    @Expose
    public Integer id;

    public LiveData(String appId, String title, String description, String channelName, String token, String updatedAt, String createdAt, Integer id) {
        this.appId = appId;
        this.title = title;
        this.description = description;
        this.channelName = channelName;
        this.token = token;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getToken() {
        return token;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "LiveData{" +
                "appId='" + appId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", channelName='" + channelName + '\'' +
                ", token='" + token + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", id=" + id +
                '}';
    }
}
