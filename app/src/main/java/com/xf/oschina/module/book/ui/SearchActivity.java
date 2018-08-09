package com.xf.oschina.module.book.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.xf.oschina.R;
import com.xf.oschina.base.BaseActivity;
import com.xf.oschina.base.BaseFragment;
import com.xf.oschina.common.CommonPagerAdapter;
import com.xf.oschina.databinding.ActivitySearchBinding;
import com.xf.oschina.module.book.ui.BookFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class SearchActivity extends BaseActivity implements HasSupportFragmentInjector {
    private ActivitySearchBinding binding;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        initeData();
    }

    private void initeData() {
        Intent intent = getIntent();
        String searchText = intent.getStringExtra("searchText");
        int searchType = intent.getIntExtra("searchType", 1);
        binding.setTitle(searchText);
        switch (searchType) {
            case 1:
//                List<BaseFragment> list = new ArrayList<>();
//                list.add(BookFragment.createBookFragment(searchText, searchText, 0));
//                binding.container.setAdapter(new CommonPagerAdapter(getSupportFragmentManager(), list));
                addFragment(BookFragment.createBookFragment(searchText, searchText, 0));
                break;
        }
    }

    private void addFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
    }

    @Override
    public void retry() {

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
