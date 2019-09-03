package com.union.bangbang.build_lib.utils;

import android.content.Context;
import android.os.CountDownTimer;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author pisa
 * @version 1.0
 * @name Android
 * @date 2019-08-11 11:18
 * @effect :
 */
public class SoftCountDownTimer extends CountDownTimer implements LifecycleObserver {
    private CountDownTimerListener mTimerListener;
    private int status = -1;

    private SoftCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long l) {
        if (status >= 3) return;
        if (mTimerListener != null) mTimerListener.onTick(l);
    }

    @Override
    public void onFinish() {
        if (status >= 3) return;
        if (mTimerListener != null) mTimerListener.onFinish();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        status = 0;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        status = 1;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onReume() {
        status = 2;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        status = 3;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        status = 4;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        status = 5;
        mTimerListener = null;
        cancel();
    }

    public static final class Builder {
        private long mMillisInFuture;
        private long mCountDownInterval;
        private CountDownTimerListener mTimerListener;
        private Context mContext;

        public Builder setMillisInFuture(long millisInFuture) {
            this.mMillisInFuture = millisInFuture;
            return this;
        }

        public Builder setCountDownInterval(long countDownInterval) {
            this.mCountDownInterval = countDownInterval;
            return this;
        }

        public Builder setTimerListener(CountDownTimerListener timerListener) {
            this.mTimerListener = timerListener;
            return this;
        }

        public <CL extends Context & LifecycleOwner> Builder(CL context) {
            mContext = context;
        }

        public SoftCountDownTimer build() {
            SoftCountDownTimer softCountDownTimer = new SoftCountDownTimer(mMillisInFuture, mCountDownInterval);
            softCountDownTimer.mTimerListener = mTimerListener;
            ((LifecycleOwner) mContext).getLifecycle().addObserver(softCountDownTimer);
            return softCountDownTimer;
        }
    }

    public interface CountDownTimerListener {
        public void onTick(long millisUntilFinished);

        public void onFinish();
    }
}
