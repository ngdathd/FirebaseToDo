package com.hdtgroup.hdt.firebasetodo.entities;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class HttpResponse {
    @SerializedName("multicast_id")
    private String multicastId;

    @SerializedName("success")
    private String success;

    @SerializedName("failure")
    private String failure;

    @SerializedName("results")
    private String results;

    @SerializedName("message_id")
    private HashMap<String, Object> messageId;

    public String getMulticastId() {
        return multicastId;
    }

    public String getSuccess() {
        return success;
    }

    public String getFailure() {
        return failure;
    }

    public String getResults() {
        return results;
    }

    public HashMap<String, Object> getMessageId() {
        return messageId;
    }
}
