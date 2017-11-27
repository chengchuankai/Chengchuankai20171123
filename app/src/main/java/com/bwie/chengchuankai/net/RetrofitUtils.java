package com.bwie.chengchuankai.net;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dy on 2017/11/2.
 */

public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;

    private RetrofitUtils() {

    }

    public static RetrofitUtils getInstance() {
        if (retrofitUtils == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    private static Retrofit retrofit;

    public static synchronized Retrofit getRetrofit(String url) {
        HttpLoggingInterceptor tag = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("TAG", message);
            }
        });

        tag.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(tag).connectTimeout(5000, TimeUnit.SECONDS).build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().client(okHttpClient).baseUrl(url).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        }
        return retrofit;

    }

    public <T> T getApiService(String url, Class<T> clazz) {
        Retrofit retrofit = getRetrofit(url);
        return retrofit.create(clazz);
    }
}
