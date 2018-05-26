package com.tpboys.unicampus.fragment.page;

import android.app.Activity;

import com.tpboys.unicampus.fragment.page.detail.HomeDetail;

public class HomePage extends BasePage {
    public HomePage(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tv_title.setText("首页");

        //TODO:临时测试detail，请完善后续
        HomeDetail detail = new HomeDetail(mActivity);
        flContainer.removeAllViews();
        flContainer.addView(detail.mRootView);
    }
}
