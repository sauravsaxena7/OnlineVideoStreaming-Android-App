package com.jlcsoftware.linkdisks.apiInterface;

import com.google.gson.JsonObject;
import com.jlcsoftware.linkdisks.ModelResponse.LoginResponse;
import com.jlcsoftware.linkdisks.ModelResponse.RegisterResponse;
import com.jlcsoftware.linkdisks.ModelResponse.UpdatePassCodeResponse;
import com.jlcsoftware.linkdisks.ModelResponse.UserOneResponse;
import com.jlcsoftware.linkdisks.login_activity.BasicAuthInterceptor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Api {


    @Headers("Content-type: application/json")
    @POST("/linkApi/create_user")
    Call<RegisterResponse> Register_body(@Body JsonObject jsonObject);




    @Headers("Content-type: application/json")
    @PUT("/linkApi/242updatePassword242")
    Call<UpdatePassCodeResponse> Update_Pass_Code_body(@Body JsonObject jsonObject);



    @Headers("Content-type: application/json")
    @GET("/linkApi/getOneuser")
    Call<List<UserOneResponse>> SendToken(@Header("x-access-token") String token);










}
