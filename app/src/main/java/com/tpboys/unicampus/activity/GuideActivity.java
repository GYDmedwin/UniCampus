package com.tpboys.unicampus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.tpboys.unicampus.R;
import com.tpboys.unicampus.utils.ConstantValue;
import com.tpboys.unicampus.utils.SpUtil;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {

    private ViewPager vp_guide;
    private Button btn_guide;
    private int[] mImageID = {R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
    private ArrayList<ImageView> mImageList;
    private LinearLayout ll_root_guide;
    private int mDis;
    private ImageView red_point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initUI();
        initData();
        initView();

        //检测布局是否全部完成，并回调方法
        vp_guide.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mDis = ll_root_guide.getChildAt(1).getLeft()-ll_root_guide.getChildAt(0).getLeft();
            }
        });

        vp_guide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                params.leftMargin = (int) (mDis*positionOffset)+position*mDis;
                red_point.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                if(position==mImageList.size()-1){
                    btn_guide.setVisibility(View.VISIBLE);
                }else{
                    btn_guide.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btn_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpUtil.setBoolean(getApplicationContext(), ConstantValue.FIRST_ENTRY,false);
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }

    private void initView() {
        MyAdapter adapter = new MyAdapter();
        vp_guide.setAdapter(adapter);

        for(int i=0;i<mImageList.size();i++){
            ImageView view = new ImageView(this);
            view.setImageResource(R.drawable.shape_point_gray);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
            );
            if(i>0) params.leftMargin=30;
            view.setLayoutParams(params);
            ll_root_guide.addView(view);
        }

    }

    class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mImageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = mImageList.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private void initData() {
        mImageList = new ArrayList<>();
        for(int i=0;i<mImageID.length;i++){
            ImageView view = new ImageView(this);
            view.setBackgroundResource(mImageID[i]);
            mImageList.add(view);
        }
    }

    private void initUI() {
        vp_guide = findViewById(R.id.vp_guide);
        btn_guide = findViewById(R.id.btn_guide);
        ll_root_guide = findViewById(R.id.ll_root_guide);
        red_point = findViewById(R.id.red_point);
    }
}
