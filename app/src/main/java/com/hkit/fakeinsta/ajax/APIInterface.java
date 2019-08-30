package com.hkit.fakeinsta.ajax;

import com.hkit.fakeinsta.model.UserVo;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIInterface {
    @POST("user/join")
    Call<Integer> join(@Body UserVo vo);


    @POST("user/login")
    Call<Integer> login(@Body UserVo vo);

    @Multipart
    @POST("insta/uploadImg")
    Call<ResponseBody> uploadImg(@Part MultipartBody.Part file, @Part("write_uid") RequestBody write_uid);

    @GET("user/test")
    Call<UserVo> test();
}
