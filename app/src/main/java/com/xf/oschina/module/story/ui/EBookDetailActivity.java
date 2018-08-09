package com.xf.oschina.module.story.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.view.View;

import com.xf.oschina.R;
import com.xf.oschina.base.BaseActivity;
import com.xf.oschina.databinding.ActivityEbookDetailBinding;
import com.xf.oschina.module.story.adapter.EBookDetailAdapter;
import com.xf.oschina.module.story.domain.EBookComment;
import com.xf.oschina.module.story.domain.EBookDetail;
import com.xf.oschina.module.story.module.EbookViewModule;
import com.xf.oschina.utils.AutoClearedValue;

import java.util.List;

import javax.inject.Inject;

public class EBookDetailActivity extends BaseActivity {
    private AutoClearedValue<ActivityEbookDetailBinding> binding;
    private AutoClearedValue<EBookDetailAdapter> mAdatpter;
    @Inject
    EbookViewModule ebookViewModule;
    private String id;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEbookDetailBinding ebookDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_ebook_detail);
        binding = new AutoClearedValue<>(this, ebookDetailBinding, ebookViewModule);
        initeData();
        initeTitleBar();
        initeRecyclerView();
    }

    private void initeTitleBar() {
        binding.get().toolbar.setNavigationIcon(AppCompatResources.getDrawable(this, R.drawable.ic_action_clear));
        binding.get().toolbar.setNavigationOnClickListener(v -> finish());
        binding.get().toolbar.setTitle(binding.get().getTitle());
    }

    private void initeData() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        binding.get().setTitle(title);
        String imgUrl = intent.getStringExtra("imgUrl");
        binding.get().setImageUrl(imgUrl);
        // 这里指定了被共享的视图元素
        ViewCompat.setTransitionName(binding.get().logo, "logo");
        id = intent.getStringExtra("id");
        ebookViewModule.refresh(id);
        ebookViewModule.loadComment(5, id);
    }

    private void initeRecyclerView() {
        mAdatpter = new AutoClearedValue<>(this, new EBookDetailAdapter(), null);
        binding.get().recyclerView.setAdapter(mAdatpter.get());
    }

    @Override
    public void retry() {
        ebookViewModule.refresh(id);
    }

    @Override
    public void refreshData(Object data) {
        if (data instanceof EBookDetail) {
            binding.get().setBookDetail((EBookDetail) data);
            binding.get().executePendingBindings();
        } else if (data instanceof List) {
            mAdatpter.get().refresh((List<EBookComment>) data);
            binding.get().executePendingBindings();
        }
    }

    public void read(View view) {
        Intent intent = new Intent(getApplicationContext(), EBookReadActivity.class);
        intent.putExtra("bookId", id);
        intent.putExtra("bookName", title);
        startActivityForResult(intent, 1);
    }
}
