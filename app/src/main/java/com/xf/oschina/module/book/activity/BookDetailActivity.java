package com.xf.oschina.module.book.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.Log;
import android.view.View;

import com.xf.oschina.R;
import com.xf.oschina.base.BaseActivity;
import com.xf.oschina.databinding.ActivityBookDetailBinding;
import com.xf.oschina.module.book.adapter.CommentAdatpter;
import com.xf.oschina.module.book.domain.Comment;
import com.xf.oschina.module.book.domain.CommentData;
import com.xf.oschina.module.book.ui.BookDetailViewModule;
import com.xf.oschina.utils.AutoClearedValue;

import javax.inject.Inject;

import me.imid.swipebacklayout.lib.SwipeBackLayout;

public class BookDetailActivity extends BaseActivity {
    @Inject
    BookDetailViewModule bookDetailViewModule;
    AutoClearedValue<ActivityBookDetailBinding> binding;
    private CommentAdatpter mAdapter;
    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBookDetailBinding bookDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_book_detail);
        binding = new AutoClearedValue<>(this, bookDetailBinding, bookDetailViewModule);
        initeData();
        initeRecyclerView();
        initeTitleBar();
        initeFetchData();
    }

    @Override
    public void retry() {
        showLoading();
        bookDetailViewModule.refresh(id);
    }

    private void initeTitleBar() {
        binding.get().toolbar.setNavigationIcon(AppCompatResources.getDrawable(this, R.drawable.ic_action_clear));
        binding.get().toolbar.setNavigationOnClickListener(v -> finish());
        binding.get().toolbar.setTitle(binding.get().getTitle());
    }

    private void initeData() {
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("url");
        binding.get().setImageUrl(imageUrl);
        binding.get().setTitle(intent.getStringExtra("title"));
        // 这里指定了被共享的视图元素
        ViewCompat.setTransitionName(binding.get().logo, "logo");
        this.id = intent.getStringExtra("id");
    }

    private void initeFetchData() {
        showLoading();
        bookDetailViewModule.refresh(id);
    }

    private void initeRecyclerView() {
        mAdapter = new CommentAdatpter();
        binding.get().recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void loadMoreData(Object data) {
        CommentData commentData = (CommentData) data;
        mAdapter.apendList(commentData.getReviews() == null ? null : commentData.getReviews());
        binding.get().executePendingBindings();
    }

    @Override
    public void refreshData(Object data) {
        CommentData commentData = (CommentData) data;
        mAdapter.refresh(commentData.getReviews() == null ? null : commentData.getReviews());
        binding.get().executePendingBindings();
    }
}
