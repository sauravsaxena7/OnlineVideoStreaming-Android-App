package com.jlcsoftware.linkdisks.client;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UploadApis {
    @Multipart
    @POST("upload/{userId}")
    Call<RequestBody> uploadImage(@Part MultipartBody.Part part, @Header("access-token") String accessToken, @Path("userId") String userId_email);
}
