package com.example.livestreamingagora.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeActiveResponse {
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public Object data;
    @SerializedName("errors")
    @Expose
    public Object errors;

    public DeActiveResponse(Boolean status, String message, Object data, Object errors) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public Object getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "DeActiveResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", errors=" + errors +
                '}';
    }
}
