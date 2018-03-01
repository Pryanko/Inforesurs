package com.example.inforesource.presentation.fragments;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.inforesource.R;
import com.example.inforesource.tools.webclients.NewsWebClient;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.inforesource.tools.Constants.API_OK;

/**
 * @author Libgo on 01.03.2018.
 */

public class WebFragment extends MvpAppCompatFragment {

    /**
     * Получаемые линки, по данной апи, не парсятся WebView.
     * WebFragment, не юзается.
     * Переходим на страницы через intent
     */

    @BindView(R.id.web_view)
    WebView webView;

    private String loadUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
           loadUrl = bundle.getString(API_OK);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_web, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startWebShow(loadUrl);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void startWebShow(String s){
        Log.d("WEB", s);
        webView.setWebViewClient( new NewsWebClient());
        webView.setWebChromeClient( new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setAllowFileAccess(true);
        //webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(s);

        
    }
}
