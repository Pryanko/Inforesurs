package com.example.inforesource.data.repository;

import android.util.Log;
import com.example.inforesource.data.News;
import com.example.inforesource.tools.rx.RxNetwork;
import java.util.List;
import io.reactivex.Observable;


/**
 * @author Libgo on 27.02.2018.
 */

public class AppRepository {


    private Integer page = 1;


    public Observable<List<News>> download() {
        page = page +1;
        Log.d("PAGE", page.toString());
        return RxNetwork.getNewsList(page);
    }

    public Observable<List<News>> searchDowload(String q){
        return RxNetwork.getSearchList(q);
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPage() {
        return page;
    }
}
