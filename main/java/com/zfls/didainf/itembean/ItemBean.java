package com.zfls.didainf.itembean;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by guwei on 16-6-24.
 */
public class ItemBean {

    public String mTitle;
    public String mTime;
    public String mImageUrl;
    public String mInforUrl;

    public ItemBean(String mTitle, String mTime, String mImageUrl , String mInforUrl) {
        this.mTitle = mTitle;
        this.mTime = mTime;
        this.mImageUrl = mImageUrl;
        this.mInforUrl = mInforUrl;
    }
}
