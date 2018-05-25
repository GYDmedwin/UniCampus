package com.tpboys.unicampus.fragment.page.detail;

import android.app.Activity;
import android.view.View;

public abstract class BaseDetail {

    public Activity mActivity;
    public View mRootView;

    public BaseDetail(Activity activity){
        this.mActivity = activity;
        mRootView = initDetailView();
    }

    public abstract View initDetailView();

    public void initDetailData(){}

}
