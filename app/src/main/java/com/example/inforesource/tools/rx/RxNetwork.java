package com.example.inforesource.tools.rx;

import com.example.inforesource.data.News;
import com.example.inforesource.network.ApiService;
import com.example.inforesource.tools.mappers.Mappers;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.example.inforesource.tools.Constants.API_KEY;
import static com.example.inforesource.tools.Constants.API_PAGE_SIZE;
import static com.example.inforesource.tools.Constants.API_SOURCES;

/**
 * @author Libgo on 27.02.2018.
 */

public class RxNetwork {

    private static ApiService apiService = ApiService.retrofit.create(ApiService.class);

    public static Observable<List<News>> getNewsList(Integer page){
        return apiService.getResponse(API_SOURCES, page, API_PAGE_SIZE, API_KEY)
                .map(Mappers::mapNewsList)
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static Observable<List<News>> getSearchList(String q){
        return apiService.getSearchResponse(API_SOURCES, q, API_KEY)
                .map(Mappers::mapNewsList)
                .observeOn(AndroidSchedulers.mainThread());
    }

}
