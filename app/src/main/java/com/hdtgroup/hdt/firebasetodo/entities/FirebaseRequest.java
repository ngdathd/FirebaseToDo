package com.hdtgroup.hdt.firebasetodo.entities;

import java.util.HashMap;

public class FirebaseRequest {
    private String token;
    private String title;
    private String body;

    public FirebaseRequest(String token, String title, String body) {
        this.token = token;
        this.title = title;
        this.body = body;
    }

    public HashMap<String, Object> bodyRequest() {
        HashMap<String, Object> bodyRequest = new HashMap<>();
        HashMap<String, Object> bodyData = new HashMap<>();
        bodyData.put("title", title);
        bodyData.put("body", body);
        bodyRequest.put("to", token);
        bodyRequest.put("notification", bodyData);
        return bodyRequest;
    }
}
