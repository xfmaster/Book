package com.xf.oschina.module.story.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.xf.oschina.R;
import com.xf.oschina.base.BaseFragment;
import com.xf.oschina.databinding.FragmentStoryBinding;
import com.xf.oschina.module.story.adapter.EbookListAdapter;
import com.xf.oschina.module.story.domain.BooksBean;
import com.xf.oschina.module.story.module.StoryViewModule;
import com.xf.oschina.utils.AutoClearedValue;

import java.util.List;

import javax.inject.Inject;

public class StoryFragment extends BaseFragment {
    private AutoClearedValue<FragmentStoryBinding> binding;
    private AutoClearedValue<EbookListAdapter> adapter;
    @Inject
    StoryViewModule storyViewModule;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentStoryBinding storyBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_story, container, false);
        binding = new AutoClearedValue<>(this, storyBinding, storyViewModule);
        return binding.get().getRoot();
    }

    public static StoryFragment createStoryFragment(String id, int currentIndex,String title) {
        Bundle bundle = new Bundle();
        bundle.putInt("currentIndex", currentIndex);
        bundle.putString("id", id);
        bundle.putString("title", title);
        StoryFragment storyFragment = new StoryFragment();
        storyFragment.setArguments(bundle);
        return storyFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EbookListAdapter mAdapter = new EbookListAdapter();
        adapter = new AutoClearedValue<>(this, mAdapter, storyViewModule);
        initeRecyclerView();
        if (getArguments().getInt("currentIndex", 0) == 0) {
            refresh();
        }
    }

    private void initeRecyclerView() {
        binding.get().ebookList.setAdapter(adapter.get());
        adapter.get().setOnItemClick(storyViewModule);
        binding.get().smartRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                storyViewModule.loadMore(getArguments().getString("id"));
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                storyViewModule.refresh(getArguments().getString("id"));
            }
        });
    }

    @Override
    public void retry() {
        storyViewModule.refresh(getArguments().getString("id"));
    }

    @Override
    public void refresh() {
        showLoading();
        storyViewModule.refresh(getArguments().getString("id"));
    }

    @Override
    public void refreshData(Object data) {
        cancelLoading();
        adapter.get().refresh((List<BooksBean>) data);
        binding.get().executePendingBindings();
        binding.get().smartRefresh.finishRefresh();
    }

    @Override
    public void loadMoreData(Object data) {
        cancelLoading();
        adapter.get().apendList((List<BooksBean>) data);
        binding.get().executePendingBindings();
        binding.get().smartRefresh.finishLoadMore();
    }
}
