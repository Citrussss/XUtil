package com.union.bangbang.build_lib.webview;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * author pisa
 * date  2019/8/14
 * version 1.0
 * effect :
 */
public abstract class ImagesJavascriptInterface {
    private Context context;
    public static final String jsCode = "javascript:(function(){ \n" +
            "            var objs = document.getElementsByTagName('img');\n" +
            "            var array=new Array(); \n" +
            "            for(var j=0;j<objs.length;j++){ \n" +
            "            array[j]=objs[j].src;\n" +
            "             } \n" +
            "            for(var i=0;i<objs.length;i++){\n" +
            "            objs[i].i=i;\n" +
            "            objs[i].onclick=function(){  \n" +
            "            \twindow.android.openImage(this.src,array,this.i);\n" +
            "            }  \n" +
            "           }    \n" +
            "       })()";

    public ImagesJavascriptInterface(Context context) {
        this.context = context;
    }

    /**
     * 记得在实现的方法上 继续加上注解 ： @android.webkit.JavascriptInterface
     * @param img
     * @param imageUrls
     * @param position
     */
    @android.webkit.JavascriptInterface
    abstract public void openImage(String img, String[] imageUrls, int position);

}
