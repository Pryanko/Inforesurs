package com.example.inforesource.tools.rx.binding;




import android.support.v7.widget.SearchView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.internal.operators.observable.ObservableFromUnsafeSource;

/**
 * @author Libgo on 01.03.2018.
 */

public final class SearchViewQueryTextChanges implements ObservableSource<String>{

    private SearchView searchView;
    private boolean initEmitter;

    public SearchViewQueryTextChanges (SearchView view){
         this.searchView = view;
    }



    @Override
    public void subscribe(Observer<? super String> observer) {



        SearchView.OnQueryTextListener watcher = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!newText.equals("")) {
                    if (initEmitter) {
                        observer.onNext(newText);
                    } else {
                        initEmitter = true;
                    }
                }
                return true;
            }
        };

        observer.onSubscribe(new MainThreadDisposable() {
            @Override
            protected void onDispose() {
                searchView.setOnQueryTextListener(null);
            }
        });

        searchView.setOnQueryTextListener(watcher);

    }

}
