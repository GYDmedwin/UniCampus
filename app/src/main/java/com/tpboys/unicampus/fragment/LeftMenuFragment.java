package com.tpboys.unicampus.fragment;

import android.view.View;

import com.tpboys.unicampus.R;

public class LeftMenuFragment extends BaseFragment {
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
        return view;
    }
}
