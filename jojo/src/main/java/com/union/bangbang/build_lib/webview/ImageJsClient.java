package com.union.bangbang.build_lib.webview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.union.bangbang.build_lib.utils.AppUtil;

/**
 * author pisa
 * date  2019/8/14
 * version 1.0
 * effect :
 */
public class ImageJsClient extends WebViewClient {

    /**
     * @param view
     * @param url
     * @deprecated
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppUtil.peekActivity().startActivity(intent);
        return true;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            AppUtil.peekActivity().startActivity(intent);
            return true;
        }
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        view.getSettings().setJavaScriptEnabled(true);
        super.onPageFinished(view, url);
        addImageClickListener(view);//待网页加载完全后设置图片点击的监听方法
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        view.getSettings().setJavaScriptEnabled(true);
        super.onPageStarted(view, url, favicon);
    }

    private void addImageClickListener(WebView webView) {
        webView.loadUrl(ImagesJavascriptInterface.jsCode);
    }
}
