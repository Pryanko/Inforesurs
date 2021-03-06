package com.example.inforesource.data.repository;

import com.example.inforesource.data.News;
import com.example.inforesource.tools.rx.RxNetwork;
import java.util.List;
import io.reactivex.Observable;

/**
 * @author Libgo on 27.02.2018.
 */

public class AppRepository {

    private Integer page = 0;

    public Observable<List<News>> download(boolean postSearch) {
        if(postSearch){
            page = 1;
            return RxNetwork.getNewsList(page);
        }
        else{
            page += 1;
            return RxNetwork.getNewsList(page);
        }

    }

    public Observable<List<News>> searchDowload(String q){
        return RxNetwork.getSearchList(q);
    }

    public void resetNumberPage() {
        this.page = 0;
    }

    public Integer getPage() {
        return page;
    }
}
