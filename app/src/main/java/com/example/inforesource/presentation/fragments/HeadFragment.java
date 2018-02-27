package com.example.inforesource.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.inforesource.R;
import com.example.inforesource.data.News;
import com.example.inforesource.presentation.adapters.NewsAdapter;
import com.example.inforesource.presentation.adapters.SpaceDecoration;
import com.example.inforesource.presentation.contracts.ContractNewsList;
import com.example.inforesource.presentation.presenters.PresenterNews;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Libgo on 27.02.2018.
 */

public class HeadFragment extends MvpAppCompatFragment implements ContractNewsList.View {

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;
    private NewsAdapter newsAdapter;


    @InjectPresenter
    PresenterNews presenterNews;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linearLayoutManager = new LinearLayoutManager(getContext());
        newsAdapter = new NewsAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_head, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init(){
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        swipeRefresh.setOnRefreshListener(presenterNews::createView);
        recyclerView.addItemDecoration(new SpaceDecoration());
        presenterNews.createView();
    }

    @Override
    public void setNewsItems(List<News> list) {
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(newsAdapter);
        newsAdapter.updateList(list);

    }

    @Override
    public void updateNewsItems(List<News> list) {

    }

    @Override
    public void loadingStateChanged(boolean isLoading) {
        swipeRefresh.setRefreshing(isLoading);
    }

    @Override
    public void errorStateChanged(Throwable throwable) {

    }
}
