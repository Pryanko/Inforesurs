package com.example.inforesource.network;

import com.example.inforesource.data.answer.Response;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.inforesource.tools.Constants.API_URL;

/**
 * @author Libgo on 27.02.2018.
 */

public interface ApiService {

    @GET("everything")
    Observable<Response> getResponse(@Query("sources") String s, @Query("page") Integer p, @Query("pagesize") Integer ps, @Query("apiKey") String ak);

    @GET("everything")
    Observable<Response> getSearchResponse(@Query("sources") String s, @Query("q") String q,@Query("apiKey") String ak);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            //Для всех запросов используется шедулер созданный выше.
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
