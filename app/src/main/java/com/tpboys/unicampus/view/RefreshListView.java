package com.tpboys.unicampus.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tpboys.unicampus.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RefreshListView extends ListView {
    private RotateAnimation mUpAnimation;
    private RotateAnimation mDownAnimation;
    private View mHeadView;
    private int startY;

    private static final int STATE_PULL_TO_REFRESH = 1;
    private static final int STATE_RELEASE_TO_REFRESH = 2;
    private static final int STATE_REFRESHING = 3;

    private int mCurrentState = STATE_PULL_TO_REFRESH;
    private TextView tv_state;
    private TextView tv_time;
    private ImageView img_arrow;
    private ProgressBar pb_loading;
    private onRefreshListener mListener;
    private int mMeasuredHeight;


    public RefreshListView(Context context) {
        super(context);
        initHeadView();
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeadView();
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHeadView();
    }


    private void initHeadView(){
        mHeadView = View.inflate(getContext(), R.layout.pull_to_refresh, null);
        tv_state = mHeadView.findViewById(R.id.tv_refresh_state);
        tv_time = mHeadView.findViewById(R.id.tv_refresh_time);
        img_arrow = mHeadView.findViewById(R.id.img_arrow);
        pb_loading = mHeadView.findViewById(R.id.pb_loading);

        mHeadView.measure(0,0);
        mMeasuredHeight = mHeadView.getMeasuredHeight();

        mHeadView.setPadding(0,-mMeasuredHeight,0,0);
        addHeaderView(mHeadView);
        initAnimation();
    }

    private void initAnimation() {
        mUpAnimation = new RotateAnimation(0, 180,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mUpAnimation.setDuration(500);
        mUpAnimation.setFillAfter(true);
        mDownAnimation = new RotateAnimation(180, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mDownAnimation.setDuration(500);
        mDownAnimation.setFillAfter(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                startY = (int) e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (mCurrentState==STATE_REFRESHING)
                    break;
                if(startY==-1)
                    startY = (int) e.getY();
                int moveY = (int) e.getY();
                int dis = moveY-startY;
                if(getFirstVisiblePosition()==0&&dis>0) {
                    mHeadView.setPadding(0, dis - mMeasuredHeight, 0, 0);
                    if (dis >= mMeasuredHeight && mCurrentState==STATE_PULL_TO_REFRESH) {
                        mCurrentState = STATE_RELEASE_TO_REFRESH;
                        refreshState();
                    }else if(dis<mMeasuredHeight && mCurrentState==STATE_RELEASE_TO_REFRESH){
                        mCurrentState = STATE_PULL_TO_REFRESH;
                        refreshState();
                    }
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if(mCurrentState==STATE_RELEASE_TO_REFRESH){
                    mHeadView.setPadding(0,0,0,0);
                    mCurrentState=STATE_REFRESHING;
                    refreshState();
                    if(mListener!=null){
                        mListener.onRefresh();
                    }
                }else if(mCurrentState==STATE_PULL_TO_REFRESH){
                    mHeadView.setPadding(0,-mMeasuredHeight,0,0);
                }
                break;
        }
        return super.onTouchEvent(e);
    }

    private void refreshState(){
        switch (mCurrentState){
            case STATE_PULL_TO_REFRESH:
                tv_state.setText("下拉刷新");
                img_arrow.setVisibility(VISIBLE);
                pb_loading.setVisibility(INVISIBLE);
                img_arrow.startAnimation(mDownAnimation);
                break;
            case STATE_RELEASE_TO_REFRESH:
                tv_state.setText("释放刷新");
                img_arrow.setVisibility(VISIBLE);
                pb_loading.setVisibility(INVISIBLE);
                img_arrow.startAnimation(mUpAnimation);
                break;
            case STATE_REFRESHING:
                tv_state.setText("刷新中...");
                img_arrow.clearAnimation();
                img_arrow.setVisibility(INVISIBLE);
                pb_loading.setVisibility(VISIBLE);
                break;
        }
    }

    private void setCurrentTime(){
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = format.format(new Date());
        tv_time.setText(date);
    }

    public void completeRefresh(boolean isSuccess){
        mHeadView.setPadding(0,-mMeasuredHeight,0,0);
        mCurrentState = STATE_PULL_TO_REFRESH;
        refreshState();
        if (isSuccess)
            setCurrentTime();
    }

    public void setOnRefreshListener(onRefreshListener l){
        this.mListener = l;
    }

    public interface onRefreshListener{
        void onRefresh();
    }
}
