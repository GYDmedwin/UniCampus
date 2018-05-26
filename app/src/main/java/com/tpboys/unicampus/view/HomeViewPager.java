package com.tpboys.unicampus.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class HomeViewPager extends ViewPager {
    private int startX;
    private int startY;

    public HomeViewPager(Context context) {
        super(context);
    }

    public HomeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                int endX = (int) ev.getX();
                int endY = (int) ev.getY();

                int dx = endX - startX;
                int dy = endY - startY;

                if (Math.abs(dy) < Math.abs(dx)) {
                    int currentItem = getCurrentItem();
                    // 左右滑动
                    if (dx > 0) {
                        // 向右划
                        if (currentItem == 0) {
                            // 第一个页面,需要拦截
                            getParent().requestDisallowInterceptTouchEvent(false);
                        }
                    }

                } else {
                    // 上下滑动,需要拦截
                    getParent().requestDisallowInterceptTouchEvent(false);
                }

                break;

            default:
                break;
        }

        return super.dispatchTouchEvent(ev);
    }


}
