package com.zfls.didainf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.zfls.didainf.itembean.ItemBean;
import com.zfls.didainf.network.ImageLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guwei on 16-6-24.
 */
public class MyAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<ItemBean> mDataList;
    private ImageLoader mImageLoader;

    public MyAdapter(Context context , List<ItemBean> list){
        mDataList = list;
        mLayoutInflater = LayoutInflater.from(context);
        mImageLoader = new ImageLoader();
    }
    //获取数据数量
    @Override
    public int getCount() {
        return mDataList.size();
    }

    //获取对应项的id
    @Override
    public long getItemId(int position) {
        return position;
    }

    //获取对应postion的item
    @Override
    public Map<String , String> getItem(int position) {
        ItemBean itemBean = mDataList.get(position);
        Map<String , String> map = new HashMap<>();
        map.put("title" , itemBean.mTitle);
        map.put("pubTime" , itemBean.mTime);
        map.put("sourceUrl" , itemBean.mInforUrl);
        return map;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = new ViewHolder();
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.list_item ,null);
            viewHolder.itempic = (ImageView) convertView.findViewById(R.id.item_pic);
            viewHolder.title = (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.time = (TextView) convertView.findViewById(R.id.item_time);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ItemBean bean = mDataList.get(position);
        viewHolder.itempic.setBackgroundResource(R.mipmap.icon_no_pic);
        //异步加载图片
//        String url = bean.mImageUrl;
//        if(url == null){
//            viewHolder.itempic.setBackgroundResource(R.mipmap.ic_launcher);
//        }else{
//            viewHolder.itempic.setTag(url);
//            mImageLoader.showImageByThread(viewHolder.itempic , url);
//        }
        viewHolder.title.setText(bean.mTitle);
        viewHolder.time.setText(bean.mTime);

        return convertView;
    }

    class ViewHolder{
        private ImageView itempic;
        private TextView title;
        private TextView time;
    }
}
