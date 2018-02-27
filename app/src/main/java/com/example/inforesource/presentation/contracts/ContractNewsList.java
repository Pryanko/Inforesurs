package com.example.inforesource.presentation.contracts;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.inforesource.data.News;

import java.util.List;

/**
 * @author Libgo on 27.02.2018.
 */

public interface ContractNewsList {

    @StateStrategyType(AddToEndSingleStrategy.class)
    interface PresenterBase {
        
        //@StateStrategyType(SkipStrategy.class)
        void createView();

        void startShow(List<News> list);

        void loadMore();

        void readMore();

        void search(String q);


    }

    @StateStrategyType(AddToEndSingleStrategy.class)
    interface View extends MvpView {

        void setNewsItems(List<News> list);

        @StateStrategyType(SkipStrategy.class)
        void updateNewsItems(List<News> list);

        void loadingStateChanged(boolean isLoading);

        void errorStateChanged(Throwable throwable);
    }

}
