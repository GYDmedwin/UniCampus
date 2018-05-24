package com.tpboys.unicampus.fragment.page;

import android.app.Activity;
import android.view.Gravity;
import android.widget.TextView;

public class BroadcastPage extends BasePage {
    public BroadcastPage(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tv_title.setText("广播");

        //TODO:临时测试detail，请完善后续
        TextView screen = new TextView(mActivity);
        screen.setText("广播——具体内容");
        screen.setGravity(Gravity.CENTER);
        flContainer.addView(screen);
    }
}
