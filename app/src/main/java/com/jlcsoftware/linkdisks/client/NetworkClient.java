package com.jlcsoftware.linkdisks.client;

import com.jlcsoftware.linkdisks.apiInterface.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {

    private static Retrofit retrofit;
    private static String BASE_URL = "https://linkdisks.herokuapp.com";

    private static NetworkClient networkClient;

    private NetworkClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized NetworkClient getInstance(){
        if(networkClient==null){
            networkClient=new NetworkClient();


        }
        return networkClient;
    }



    public Api getApi(){
        return retrofit.create(Api.class);
    }



}
