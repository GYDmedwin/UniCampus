package com.tpboys.unicampus.fragment.page.detail;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tpboys.unicampus.R;
import com.tpboys.unicampus.view.HomeViewPager;
import com.tpboys.unicampus.view.RefreshListView;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class HomeDetail extends BaseDetail {

    private RefreshListView lv_home;
    //测试用集合
    private List<String> test;
    private List<ImageView> test1;


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

        //TODO:测试用lv数据,请完善后续
        test = new ArrayList<>();
        for(int i=0;i<20;i++){
            String s = "测试" + i;
            test.add(s);
        }

        //TODO:测试用vp数据，请完善后续
        int[] test2 =
                {R.drawable.test_1,R.drawable.test_2,R.drawable.test_3,R.drawable.test_4};
        test1 = new ArrayList<>();
        for(int i =0;i<test2.length;i++){
            ImageView view = new ImageView(mActivity);
            view.setImageResource(test2[i]);
            test1.add(view);
        }
        initViewPager();

        LVAdapter adapter = new LVAdapter();
        lv_home.setAdapter(adapter);

    }

    //直至结尾代码均为测试用，请后续修改完善
    class LVAdapter extends BaseAdapter{

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

    private void initViewPager() {
        View view_viewpager = View.inflate(mActivity, R.layout.viewpager_home_detail, null);
        lv_home.addHeaderView(view_viewpager);
        HomeViewPager vp_home_detail = view_viewpager.findViewById(R.id.vp_home_detail);
        final TextView tv_vp_home_title = view_viewpager.findViewById(R.id.tv_vp_home_title);
        CirclePageIndicator vpi_home_detail = view_viewpager.findViewById(R.id.vpi_home_detail);

        vp_home_detail.setAdapter(new VPAdapter());
        vpi_home_detail.setViewPager(vp_home_detail);

        tv_vp_home_title.setText("测试1");
        vpi_home_detail.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                 tv_vp_home_title.setText("测试"+(position+1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class VPAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return test1.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = test1.get(position);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
