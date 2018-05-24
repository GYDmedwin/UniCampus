package com.tpboys.unicampus.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tpboys.unicampus.R;
import com.tpboys.unicampus.fragment.ContentFragment;
import com.tpboys.unicampus.fragment.LeftMenuFragment;
import com.tpboys.unicampus.utils.ConstantValue;

public class MainActivity extends AppCompatActivity {

    public SlidingMenu mSlidingMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSlidingMenu = new SlidingMenu(this);
        mSlidingMenu.setMode(SlidingMenu.LEFT);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        mSlidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        mSlidingMenu.setMenu(R.layout.left_menu);
        mSlidingMenu.setBehindOffset(300);

        initFragment();
    }

    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_left_menu,new LeftMenuFragment(), ConstantValue.LEFT_MENU);
        transaction.replace(R.id.fl_main,new ContentFragment(),ConstantValue.CONTENT);
        transaction.commit();
    }
}
