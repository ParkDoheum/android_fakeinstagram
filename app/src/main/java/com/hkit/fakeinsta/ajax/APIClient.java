package com.hkit.fakeinsta.ajax;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retro = null;

    public static Retrofit getClient() {
        //HttpLoggingIntercepter intercepter = new HttpLoggingIntercepter();

        // Create a new object from HttpLoggingInterceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Add Interceptor to HttpClient
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if(retro == null) {
            retro = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.78:8099/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retro;
    }
}
