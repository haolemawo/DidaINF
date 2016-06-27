package com.zfls.didainf.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by guwei on 16-6-25.
 */
public class ImageLoader {

    private ImageView mImageView;
    private String mUrl;
    //创建缓存(系统自带)
    private LruCache<String , Bitmap> mCaches;

    public ImageLoader(){
        //获取最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        //设置缓存大小
        int cacheSize = maxMemory/3;
        mCaches = new LruCache<String  , Bitmap>(cacheSize){

            @Override
            protected int sizeOf(String key, Bitmap value) {
                //每次存入缓存时调用 , 返回bitmap的大小
                return super.sizeOf(key, value);
            }
        };
    }

    //添加图片到缓存  caches 类似map 是key value 类型
    public void addBitmapToCaches(String url , Bitmap bitmap){
        //判断缓存中是否存在该图片
        if(getBitmapFormCache(url) == null){
            mCaches.put(url , bitmap);
        }


    }

    private Bitmap getBitmapFormCache(String url) {
        return mCaches.get(url);
    }

    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(mImageView.getTag().equals(mUrl)){
                mImageView.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };

    public void showImageByThread(final  ImageView image , final String url) {

        mImageView = image;
        mUrl = url;
        //从缓存村中取出对用url的图片
        Bitmap bitmap = getBitmapFormCache(url);
        if(bitmap == null){
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    Bitmap bitmap = getBitmapFormURL(url);
                    Message msg = Message.obtain();
                    msg.obj = bitmap;
                    handler.sendMessage(msg);
                }
            };
        }else{
            image.setImageBitmap(bitmap);
        }
    }

    private Bitmap getBitmapFormURL(String urlString) {

        Bitmap bitmap = null;
        InputStream is = null;

        URL url = null;
        try {
            url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);//??
            connection.disconnect();
            addBitmapToCaches(urlString , bitmap);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bitmap;
    }
}
