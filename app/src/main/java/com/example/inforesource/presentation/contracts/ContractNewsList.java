package com.example.inforesource.presentation.contracts;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.inforesource.data.News;

import java.util.List;

/**
 * @author Libgo on 27.02.2018.
 */

public interface ContractNewsList {

   
    interface PresenterBase {


        void refresh(boolean postSearch, boolean scrollTop);

        void startShow(List<News> list, boolean postSearch, boolean scrollTop);

        void search(String q);

        void destroyView();


        void searchShow(List<News> list);
    }

    @StateStrategyType(AddToEndStrategy.class)
    interface View extends MvpView {

        void isCheckedButtonVisible();

        void setNewsItems(List<News> list, boolean postSearch, boolean scrollTop);

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
