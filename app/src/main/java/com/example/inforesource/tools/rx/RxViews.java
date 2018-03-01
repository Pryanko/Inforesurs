package com.example.inforesource.tools.rx;

import android.support.v7.widget.SearchView;

import com.example.inforesource.tools.rx.binding.SearchViewQueryTextChanges;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author Libgo on 01.03.2018.
 */

public class RxViews {

    public static Observable<String> queryTextChanges(SearchView view, long debounce) {

        return Observable.unsafeCreate(new SearchViewQueryTextChanges(view))
                .debounce(debounce, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread());

    }

}
