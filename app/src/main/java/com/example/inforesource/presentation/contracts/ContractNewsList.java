package com.example.inforesource.presentation.contracts;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.inforesource.data.News;

import java.util.List;

/**
 * @author Libgo on 27.02.2018.
 */

public interface ContractNewsList {

   
    interface PresenterBase {


        void refresh();

        void startShow(List<News> list);

        void search(String q);

        void destroyView();


        void searchShow(List<News> list);
    }


    interface View extends MvpView {

        void isCheckedButtonVisible();

        void setNewsItems(List<News> list);

        @StateStrategyType(SkipStrategy.class)
        void updateNewsItems(List<News> list);
        
        void loadingStateChanged(boolean isLoading);

        void errorStateChanged(Throwable throwable);

        void isRecyclerListener(boolean isOnListener);
    }

    interface ClickItemAdapter{

         void readMore(String s);

    }

}
