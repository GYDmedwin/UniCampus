package com.tpboys.unicampus.fragment.page;

import android.app.Activity;
import android.view.Gravity;
import android.widget.TextView;

public class CommunityPage extends BasePage {
    public CommunityPage(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tv_title.setText("社区");

        //TODO:临时测试detail，请完善后续
        TextView screen = new TextView(mActivity);
        screen.setText("社区——具体内容");
        screen.setGravity(Gravity.CENTER);
        flContainer.addView(screen);
    }
}
