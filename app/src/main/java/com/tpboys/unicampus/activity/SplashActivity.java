package com.tpboys.unicampus.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.tpboys.unicampus.R;
import com.tpboys.unicampus.utils.ConstantValue;
import com.tpboys.unicampus.utils.SpUtil;

public class SplashActivity extends AppCompatActivity {

    private LinearLayout ll_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ll_root = findViewById(R.id.ll_root_splash);
        initAnimation();
    }

    //TODO：检测版本更新

    /**
     * 初始化动画
     */
    private void initAnimation() {
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(2000);
        ll_root.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                nextPage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 跳转到下一个页面，如果是第一次，则跳转到引导页
     */
    private void nextPage() {
        boolean isFirstTime = SpUtil.getBoolean(getApplicationContext(), ConstantValue.FIRST_ENTRY, true);
        Intent intent;
        if(isFirstTime){
            intent = new Intent(getApplicationContext(),GuideActivity.class);
        }else{
            intent = new Intent(getApplicationContext(),MainActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
