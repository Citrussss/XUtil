package com.union.bangbang.zero.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.union.bangbang.zero.R;

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2019-01-27 19:14
 * 只有编译器可能不骗你。
 */
public class ClipLayout extends FrameLayout {
    private float[] radii = new float[8];
    private Path path;
    private RectF rectF = new RectF();
    private Paint paint = new Paint();
    private final String TAG = "ClipLayout";

    public ClipLayout(Context context) {
        this(context, null);
    }

    public ClipLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ClipLayout);
        radii[0] = radii[1] = array.getFloat(R.styleable.ClipLayout_lt, 0);
        radii[2] = radii[3] = array.getFloat(R.styleable.ClipLayout_rt, 0);
        radii[4] = radii[5] = array.getFloat(R.styleable.ClipLayout_rb, 0);
        radii[6] = radii[7] = array.getFloat(R.styleable.ClipLayout_lb, 0);
        array.recycle();
        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF.left = getPaddingLeft();
        rectF.top = getPaddingTop();
        rectF.right = w - getPaddingRight();
        rectF.bottom = h - getPaddingBottom();
        path.reset();
        path.addRoundRect(rectF, radii, Path.Direction.CW);
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), null);
        } else {
            canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), null, Canvas
                    .ALL_SAVE_FLAG);
        }
        super.dispatchDraw(canvas);
        canvas.drawPath(path, paint);
        canvas.restore();
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        return super.drawChild(canvas, child, drawingTime);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


}
