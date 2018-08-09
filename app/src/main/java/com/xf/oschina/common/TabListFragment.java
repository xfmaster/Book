package com.xf.oschina.common;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xf.oschina.R;
import com.xf.oschina.base.BaseFragment;
import com.xf.oschina.databinding.FragmentTabListBinding;
import com.xf.oschina.module.book.ui.BookFragment;
import com.xf.oschina.module.home.MainActivity;
import com.xf.oschina.module.story.ui.StoryFragment;
import com.xf.oschina.utils.AutoClearedValue;

import java.util.ArrayList;
import java.util.List;

public class TabListFragment extends BaseFragment {
    AutoClearedValue<FragmentTabListBinding> binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentTabListBinding tabListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab_list, container, false);
        binding = new AutoClearedValue<>(this, tabListBinding, null);
        return binding.get().getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initeViewPager();
        initeTab();
    }

    private void initeTab() {
        binding.get().tab.setupWithViewPager(binding.get().viewpager);
    }

    private void initeViewPager() {
        List<BaseFragment> list = new ArrayList<>();
        int type = getArguments().getInt("type", 1);
        if (type == 1) {
            list.add(BookFragment.createBookFragment("推荐", "推荐", 0));
            list.add(BookFragment.createBookFragment("hot", "热点", 1));
            list.add(BookFragment.createBookFragment("新书", "新书", 2));
            list.add(BookFragment.createBookFragment("小说", "小说", 3));
        } else if (type == 2) {
            list.add(StoryFragment.createStoryFragment("54d42d92321052167dfb75e3",0,"最热"));
            list.add(StoryFragment.createStoryFragment("564547c694f1c6a144ec979b",1,"留存"));
            list.add(StoryFragment.createStoryFragment("564eb878efe5b8e745508fde",2,"完结"));
            list.add(StoryFragment.createStoryFragment("564547c694f1c6a144ec979b",3,"潜力"));
        }
        binding.get().viewpager.setAdapter(new CommonPagerAdapter(getChildFragmentManager(), list));
        binding.get().viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                list.get(position).refresh();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        binding.get().viewpager.setOffscreenPageLimit(list.size());
    }


    @Override
    public void retry() {

    }
}
