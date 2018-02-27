package com.example.inforesource.presentation.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.inforesource.App;
import com.example.inforesource.data.News;
import com.example.inforesource.data.repository.AppRepository;
import com.example.inforesource.presentation.contracts.ContractNewsList;

import java.util.List;

/**
 * @author Libgo on 27.02.2018.
 */
@InjectViewState
public class PresenterNews extends MvpPresenter<ContractNewsList.View> implements ContractNewsList.PresenterBase {

    private AppRepository appRepository = App.getAppComponent().getAppRepository();


    @Override
    public void createView() {
        getViewState().loadingStateChanged(true);
        appRepository.initPresenter(this);
        appRepository.getDataList();
        
    }
    @Override
    public void startShow(List<News> list){
        getViewState().setNewsItems(list);
    }

    @Override
    public void loadMore() {
        getViewState().loadingStateChanged(false);
    }

    @Override
    public void readMore() {

    }

    @Override
    public void search(String q) {

    }
}
