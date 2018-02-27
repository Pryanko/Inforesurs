package com.example.inforesource.presentation.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.inforesource.R;
import com.example.inforesource.presentation.fragments.HeadFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Libgo on 26.02.2018.
 */

public class HeadActivity extends MvpAppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
        ButterKnife.bind(this);
        initToolBar();

        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new HeadFragment())
                    .commit();
        }
    }

    private void initToolBar(){
        toolbar.setTitle(R.string.app_title_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);

    }
}
