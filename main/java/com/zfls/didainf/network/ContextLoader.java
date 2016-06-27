package com.zfls.didainf.network;

import android.content.Context;

import java.io.InputStream;

/**
 * Created by guwei on 16-6-26
 */
public class ContextLoader {

    private  Context mContext;
    private String mUrl;

    public ContextLoader(Context context , String url){
        this.mContext = context;
        this.mUrl = url;
    }


}
