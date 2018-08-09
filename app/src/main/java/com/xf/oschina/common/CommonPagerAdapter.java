package com.xf.oschina.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xf.oschina.base.BaseFragment;

import java.util.List;

public class CommonPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> list;

    public CommonPagerAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public BaseFragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Bundle bundle = list.get(position).getArguments();
        if (bundle == null)
            return "";
        return bundle.getString("title", "");
    }
}
