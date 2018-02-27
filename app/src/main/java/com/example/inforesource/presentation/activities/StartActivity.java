package com.example.inforesource.presentation.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.inforesource.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Libgo on 26.02.2018.
 */

public class StartActivity extends AppCompatActivity {

    @BindView(R.id.image_logo)
    SimpleDraweeView imageLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        startShow();

    }

    private void startShow(){
        imageLogo.setImageResource(R.drawable.ic_logo);
        imageLogo.animate().alpha(1.0f).setDuration(1500).setListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                endShow();
            }
        });
    }

    private void endShow() {
        startActivity(new Intent(this, HeadActivity.class));
        finish();
    }
}
