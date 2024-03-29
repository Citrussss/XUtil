package com.union.bangbang.build_lib.utils;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * author  :  xiaolongbao
 * date    :  2019/3/27  14:18
 * desc    :  计算RecyclerView间距
 * version :  v1.0
 */


public abstract class SpacesItemDecorationEntrust {
    //color的传入方式是resouce.getcolor
    protected Drawable mDivider;

    protected int leftRight;

    protected int topBottom;

    public SpacesItemDecorationEntrust(int leftRight, int topBottom, int mColor) {
        this.leftRight = leftRight;
        this.topBottom = topBottom;
        if (mColor != 0) {
            mDivider = new ColorDrawable(mColor);
        }
    }

    abstract void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state);

    abstract void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state);

}
