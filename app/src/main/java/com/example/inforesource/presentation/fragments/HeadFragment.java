package com.example.inforesource.presentation.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.inforesource.R;
import com.example.inforesource.data.News;
import com.example.inforesource.presentation.adapters.NewsAdapter;
import com.example.inforesource.presentation.adapters.NewsScrollListener;
import com.example.inforesource.presentation.adapters.SpaceDecoration;
import com.example.inforesource.presentation.contracts.ContractNewsList;
import com.example.inforesource.presentation.presenters.PresenterNews;
import com.example.inforesource.tools.rx.RxViews;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import info.hoang8f.widget.FButton;
import io.reactivex.disposables.Disposable;

/**
 * @author Libgo on 27.02.2018.
 */

public class HeadFragment extends MvpAppCompatFragment implements ContractNewsList.View,
        ContractNewsList.ClickItemAdapter, View.OnAttachStateChangeListener {

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.button_load)
    FButton buttonLoad;

    private LinearLayoutManager linearLayoutManager;
    private NewsAdapter newsAdapter;
    private NewsScrollListener newsScrollListener;
    private Disposable disposable;
    private SearchView searchView;

    @InjectPresenter
    PresenterNews presenterNews;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init settings recycler
        linearLayoutManager = new LinearLayoutManager(getContext());
        newsAdapter = new NewsAdapter();
        newsAdapter.initClickItem(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_head, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        init();
        return view;
    }

    private void init() {
        //inits
        //recycler
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.addItemDecoration(new SpaceDecoration());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        //recycler listener
        newsScrollListener = new NewsScrollListener(recyclerView, buttonLoad);
        //swipeLayout
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        swipeRefresh.setEnabled(false);
        //button
        buttonLoad.setButtonColor(0xE77083FF);
        buttonLoad.setShadowEnabled(false);
        buttonLoad.setCornerRadius(4);
        buttonLoad.setOnClickListener(v -> {
            presenterNews.refresh(false, false);
            buttonLoad
                    .setVisibility(View.GONE);
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);

        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.addOnAttachStateChangeListener(this);
        disposable = RxViews.queryTextChanges(searchView, 600)
                .subscribe(presenterNews::search);

    }


    @Override
    public void isCheckedButtonVisible() {
        if (buttonLoad.getVisibility() == View.VISIBLE) {
            buttonLoad.setVisibility(View.GONE);
        }
    }

    @Override
    public void setNewsItems(List<News> list, boolean postSearch, boolean scrollTop) {
        newsAdapter.addList(list, postSearch);
        if(scrollTop){
            linearLayoutManager.scrollToPosition(0);
        }


    }

    @Override
    public void updateNewsItems(List<News> list) {
        newsAdapter.updateList(list);
        searchView.clearFocus();
        linearLayoutManager.scrollToPosition(0);
    }

    @Override
    public void loadingStateChanged(boolean isLoading) {
        swipeRefresh.setRefreshing(isLoading);
    }

    @Override
    public void errorStateChanged(Throwable throwable) {
        Log.e("NetworkError", throwable.toString());
        Snackbar.make(swipeRefresh, R.string.not_network, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.repeat, v ->
                {presenterNews.refresh(false, false);
                 presenterNews.resetNumberPage();}
                ).show();

    }

    @Override
    public void isRecyclerListener(boolean isOnListener) {
        if (isOnListener) {
            recyclerView.addOnScrollListener(newsScrollListener);
        } else {
            recyclerView.clearOnScrollListeners();
        }
    }

    @Override
    public void readMore(String s) {
        /*
        WebFragment webFragment = new WebFragment();
        Bundle bundle = new Bundle();
        bundle.putString(API_OK, s);
        webFragment.setArguments(bundle);
        if(getFragmentManager().beginTransaction() != null) {
            getFragmentManager().beginTransaction().addToBackStack(API_OK)
                    .replace(R.id.fragment_container, webFragment)
                    .commit();
        }  */
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(s));
        startActivity(i);

    }

    @Override
    public void onViewAttachedToWindow(View v) {

    }

    @Override
    public void onViewDetachedFromWindow(View v) {
              presenterNews.refresh(true, true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRecyclerListener(false);
        disposable.dispose();
        presenterNews.destroyView();

    }



}


