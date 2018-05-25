package com.tpboys.unicampus.fragment.page.detail;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tpboys.unicampus.R;
import com.tpboys.unicampus.view.RefreshListView;

import java.util.ArrayList;
import java.util.List;

public class HomeDetail extends BaseDetail {

    private RefreshListView lv_home;
    //测试用集合
    private List<String> test;

    public HomeDetail(Activity activity) {
        super(activity);
    }

    @Override
    public View initDetailView() {
        View view = View.inflate(mActivity, R.layout.detail_home, null);
        lv_home = view.findViewById(R.id.lv_home_detail);
        initDetailData();
        return view;
    }

    @Override
    public void initDetailData() {

        //TODO:测试用数据,请完善后续
        test = new ArrayList<>();
        for(int i=0;i<20;i++){
            String s = "测试" + i;
            test.add(s);
        }

        HomeDetailAdapter adapter = new HomeDetailAdapter();
        lv_home.setAdapter(adapter);

    }

    //测试用，请后续修改完善
    class HomeDetailAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return test.size();
        }

        @Override
        public Object getItem(int position) {
            return test.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = View.inflate(mActivity,R.layout.list_item_home,null);
            }
            TextView tv_test = convertView.findViewById(R.id.tv_test);
            tv_test.setText(test.get(position));
            return convertView;
        }
    }
}
