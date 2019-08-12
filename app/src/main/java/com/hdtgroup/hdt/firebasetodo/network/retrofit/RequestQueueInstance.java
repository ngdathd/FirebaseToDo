package com.hdtgroup.hdt.firebasetodo.network.retrofit;

public class RequestQueueInstance {
    private static final RequestQueueInstance ourInstance = new RequestQueueInstance();

//    private RequestQueue requestQueue;

    public static RequestQueueInstance getInstance() {
        return ourInstance;
    }

    private RequestQueueInstance() {
    }
}
