package com.zfls.didainf;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.zfls.didainf.itembean.ItemBean;
import com.zfls.didainf.network.NetWork;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.zfls.didainf.network.NetWork.SEARCH;

/**
 * Created by guwei on 16-6-23.
 */
public class FunnyFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView mListview;
    List<ItemBean> itemBeenlist = new ArrayList<>();

    //请求的页码
    int page = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_topical , container , false);

        initView(view);

        getDate();
        return view;
    }


    private void initView(View view) {
        mListview = (ListView) view.findViewById(R.id.tab_topical_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshlayout);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorRed , R.color.colorGre , R.color.colorBlu);
        mSwipeRefreshLayout.setProgressViewEndTarget(true , 150);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String  , String> map = new MyAdapter(getContext() , itemBeenlist).getItem(position);
                Intent intent = new Intent(getActivity() , Webview.class);
                String title = map.get("title");
                String url = map.get("sourceUrl");
                Bundle bundle = new Bundle();
                bundle.putString("title" , title);
                bundle.putString("url" , url);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


    //滑动手势监听 , 加载数据
    @Override
    public void onRefresh() {
        Toast.makeText(getActivity() , "loding..." , Toast.LENGTH_SHORT).show();
        getDate();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void getDate() {
        final String url = SEARCH;
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url+"11&page="+page, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            int retCode = jsonObject.getInt("retCode");
                            Log.i("TAG" , retCode+"");
                            Log.i("TAG" , url+"11&page="+page);
                            if(retCode == 200){
                                JSONObject result = jsonObject.getJSONObject("result");
                                JSONArray arrobj = result.getJSONArray("list");
                                for(int i=0 ; i<arrobj.length() ; i++){
                                    JSONObject item = arrobj.getJSONObject(i);
                                    String title = item.getString("title");
                                    String pubTime = item.getString("pubTime");
                                    String sourceUrl = item.getString("sourceUrl");
                                    //String thumbnails = item.getString("thumbnails");
                                    itemBeenlist.add(new ItemBean( title ,pubTime , null, sourceUrl));
                                    Log.i("TAG" , title+"\t"+pubTime+"\t"+sourceUrl);
                                }
                                mListview.setAdapter(new MyAdapter(getContext() , itemBeenlist));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getActivity() , "loding..." , Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonObjectRequest);
        ++page;
    }

}
