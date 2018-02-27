package com.example.inforesource.data.repository;

import android.util.Log;

import com.example.inforesource.data.News;
import com.example.inforesource.presentation.contracts.ContractNewsList;
import com.example.inforesource.tools.rx.RxNetwork;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author Libgo on 27.02.2018.
 */

public class AppRepository {

    private Disposable disposable;
    private Integer page = 1;
    private List<News> dataList;
    private ContractNewsList.PresenterBase presenterBase;


    public void initPresenter(ContractNewsList.PresenterBase presenterBase){
        this.presenterBase = presenterBase;

    }

    public void getDataList(){
        disposable = RxNetwork.getNewsList(page)
                .subscribe(this::logs, throwable -> error());
    }

    private void error() {
    }

    private void logs(List<News> list) {
        page = page + 1;
        for (News news:list){
            Log.d("NEWS", news.getTitle());
        }
        presenterBase.startShow(list);
        presenterBase.loadMore();


    }


}
