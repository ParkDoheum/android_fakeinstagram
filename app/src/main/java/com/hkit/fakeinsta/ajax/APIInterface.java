package com.hkit.fakeinsta.ajax;

import com.hkit.fakeinsta.model.UserVo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface APIInterface {
    @POST("user/join")
    Call<Integer> join(@Body UserVo vo);

    @POST("user/login")
    Call<Integer> login(@Body UserVo vo);

    @GET("user/test")
    Call<UserVo> test();
}
