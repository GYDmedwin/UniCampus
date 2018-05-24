package com.tpboys.unicampus.fragment;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.tpboys.unicampus.R;
import com.tpboys.unicampus.fragment.page.BasePage;
import com.tpboys.unicampus.fragment.page.BroadcastPage;
import com.tpboys.unicampus.fragment.page.CommunityPage;
import com.tpboys.unicampus.fragment.page.HomePage;
import com.tpboys.unicampus.fragment.page.MessagePage;
import com.tpboys.unicampus.view.NoScrollViewPager;

import java.util.ArrayList;

public class ContentFragment extends BaseFragment {

    private NoScrollViewPager vp_content;
    private ArrayList<BasePage> mPageList;
    private RadioGroup rg_tab_group;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        vp_content = view.findViewById(R.id.vp_content);
        rg_tab_group = view.findViewById(R.id.rg_tab_group);
        return view;
    }

    @Override
    public void initData() {
        initPageList();

        MyAdapter adapter = new MyAdapter();
        vp_content.setAdapter(adapter);
        rg_tab_group.check(R.id.tab_home);
        rg_tab_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.tab_home:
                        vp_content.setCurrentItem(0,false);
                        break;
                    case R.id.tab_broadcast:
                        vp_content.setCurrentItem(1,false);
                        break;
                    case R.id.tab_community:
                        vp_content.setCurrentItem(2,false);
                        break;
                    case R.id.tab_message:
                        vp_content.setCurrentItem(3,false);
                        break;

                }
            }
        });
    }
    class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mPageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePage page = mPageList.get(position);
            View view = page.mRootView;
            page.initData();
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private void initPageList(){
        mPageList = new ArrayList<>();
        mPageList.add(new HomePage(mActivity));
        mPageList.add(new BroadcastPage(mActivity));
        mPageList.add(new CommunityPage(mActivity));
        mPageList.add(new MessagePage(mActivity));
    }
}
