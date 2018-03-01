package com.example.inforesource.presentation.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.inforesource.App;
import com.example.inforesource.data.News;
import com.example.inforesource.data.repository.AppRepository;
import com.example.inforesource.presentation.contracts.ContractNewsList;
import java.util.List;
import io.reactivex.disposables.CompositeDisposable;

/**
 * @author Libgo on 27.02.2018.
 */
@InjectViewState
public class PresenterNews extends MvpPresenter<ContractNewsList.View>
        implements ContractNewsList.PresenterBase{

    private AppRepository appRepository = App.getAppComponent().getAppRepository();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onFirstViewAttach() {
        refresh();
    }
    @Override
    public void refresh(){

        getViewState().loadingStateChanged(true);
        compositeDisposable.add(appRepository.download()
                .subscribe(this::startShow, this::handleError));
    }

    private void handleError(Throwable throwable) {
        getViewState().loadingStateChanged(false);
        getViewState().errorStateChanged(throwable);
    }

    @Override
    public void startShow(List<News> list){
        getViewState().setNewsItems(list);
        getViewState().loadingStateChanged(false);
        getViewState().isRecyclerListener(true);
        getViewState().isCheckedButtonVisible();
    }


    @Override
    public void searchShow(List<News> list) {
        getViewState().updateNewsItems(list);
        getViewState().loadingStateChanged(false);
        getViewState().isRecyclerListener(false);
        getViewState().isCheckedButtonVisible();
    }


    @Override
    public void search(String q) {
        getViewState().loadingStateChanged(true);
        compositeDisposable.add(appRepository.searchDowload(q)
                .subscribe(this::searchShow, this::handleError));
    }

    public void setNumberPage(){
        appRepository.setPage(1);
    }

    @Override
    public void destroyView() {
        if(compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }



}
