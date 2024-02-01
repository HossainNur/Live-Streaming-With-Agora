package com.example.livestreamingagora.models;

public class LiveBody {
    private String app_id,title,description,channel_name,token;

    public LiveBody(String app_id, String title, String description, String channel_name, String token) {
        this.app_id = app_id;
        this.title = title;
        this.description = description;
        this.channel_name = channel_name;
        this.token = token;
    }
}
