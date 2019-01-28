package com.union.bangbang.zero.toast;

import com.union.bangbang.zero.AppUtil;

import com.union.bangbang.zero.toast.tastytoast.TastyToast;
import rx.Observable
import rx.schedulers.Schedulers

/**
 * @name money
 * @class name：com.jby.money.base.util.audiorecord
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/20 8:41 PM
 * @change
 * @chang time
 * @class describe
 */
class JoJoToast : TastyToast() {
    companion object {
        fun error(e: Throwable) {
            makeText(
                AppUtil.peekActivity(),
                e.toString(),
                TastyToast.LENGTH_LONG,
                TastyToast.ERROR
            )
        }

        fun message(o: Any) {
            makeText(
                AppUtil.peekActivity(),
                o.toString(),
                TastyToast.LENGTH_LONG,
                TastyToast.INFO
            )
        }
    }
}
/*
public class JoJoToast extends TastyToast {
    public static void error(Throwable e) {
        Disposable subscribe = Observable.just(e).observeOn(Schedulers.newThread())
                .subscribe(throwable ->
                        makeText(AppUtil.peekActivity(), throwable.toString(), TastyToast.LENGTH_LONG, TastyToast.ERROR)
                );
    }

    public static void message(Object o) {
        Disposable subscribe = Observable.just(o).observeOn(Schedulers.newThread())
                .subscribe(throwable ->
                        makeText(AppUtil.peekActivity(), throwable.toString(), TastyToast.LENGTH_LONG, TastyToast.INFO)
                );
    }
}
*/
