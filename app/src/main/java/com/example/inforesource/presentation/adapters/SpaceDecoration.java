package com.example.inforesource.presentation.adapters;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Libgo on 27.02.2018.
 */

public class SpaceDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int space = (int) Math.ceil(16 * Resources.getSystem().getDisplayMetrics().density);
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = space;
        }
        outRect.bottom = outRect.right = outRect.left = space;
    }
}