package com.example.inforesource.presentation.adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import info.hoang8f.widget.FButton;

/**
 * @author Libgo on 01.03.2018.
 */

public class NewsScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager linearLayoutManager;
    private NewsAdapter newsAdapter;
    private FButton buttonLoad;

    public NewsScrollListener(RecyclerView recyclerView, FButton buttonLoad){
            this.linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            this.newsAdapter = (NewsAdapter) recyclerView.getAdapter();
            this.buttonLoad = buttonLoad;

    }
    

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if(linearLayoutManager.findLastVisibleItemPosition() + 1  >= newsAdapter.getItemCount()){
           isButtonVisible(true);
        } else {
            isButtonVisible(false);
        }

    }

    private void isButtonVisible(boolean isVisible){
        if(isVisible){
            buttonLoad.setVisibility(View.VISIBLE);
            buttonLoad.animate().translationY(0).alpha(1.0f).setDuration(600)
                    .setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                }
            });

        }
        else {
            buttonLoad.animate().translationY(buttonLoad.getHeight()).alpha(0.0f).setDuration(450)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            buttonLoad.setVisibility(View.GONE);
                        }
                    });
        }
    }
}
