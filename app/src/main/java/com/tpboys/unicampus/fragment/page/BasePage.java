package com.tpboys.unicampus.fragment.page;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tpboys.unicampus.R;
import com.tpboys.unicampus.activity.MainActivity;

public class BasePage {
    public Activity mActivity;
    public View mRootView;
    public FrameLayout flContainer;
    public TextView tv_title;
    public ImageButton btn_sliding;

    public BasePage(Activity activity){
        this.mActivity = activity;
        initUI();
    }
    private void initUI(){
        mRootView = View.inflate(mActivity, R.layout.page_base, null);
        flContainer = mRootView.findViewById(R.id.fl_page);
        tv_title = mRootView.findViewById(R.id.tv_title);
        btn_sliding = mRootView.findViewById(R.id.btn_sliding);

        btn_sliding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });
    }

    private void toggle(){
        MainActivity mainActivity = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainActivity.mSlidingMenu;
        slidingMenu.toggle();
    }

    public void initData(){}
}
