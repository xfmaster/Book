package com.xf.oschina.module.story.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.xf.oschina.R;
import com.xf.oschina.base.BaseActivity;
import com.xf.oschina.databinding.ActivityEbookReadBinding;
import com.xf.oschina.module.story.adapter.BookReadAdapter;
import com.xf.oschina.module.story.domain.BookChapter;
import com.xf.oschina.module.story.domain.ChapterPager;
import com.xf.oschina.module.story.domain.ChapterRead;
import com.xf.oschina.module.story.module.EBookReadViewModule;
import com.xf.oschina.utils.AutoClearedValue;
import com.xf.oschina.utils.LogUtils;
import com.xf.oschina.utils.ReadUtils;
import com.xf.oschina.view.ViewPagerTransform;
import com.xf.oschina.view.book.listener.PageTurningListener;

import java.util.List;

import javax.inject.Inject;

public class EBookReadActivity extends BaseActivity {
    @Inject
    EBookReadViewModule eBookReadViewModule;
    private AutoClearedValue<ActivityEbookReadBinding> binding;
//    private AutoClearedValue<BookReadAdapter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActivityEbookReadBinding ebookReadBinding = DataBindingUtil.setContentView(this, R.layout.activity_ebook_read);
        binding = new AutoClearedValue<>(this, ebookReadBinding, eBookReadViewModule);
        ReadUtils.setBookTextConfig(this);
//        BookReadAdapter bookReadAdapter = new BookReadAdapter(eBookReadViewModule,binding.get().viewpager);
//        adapter = new AutoClearedValue<>(this, bookReadAdapter, eBookReadViewModule);
        inteData();
    }

    private void inteData() {
        Intent intent = getIntent();
        String bookId = intent.getStringExtra("bookId");
        String bookName = intent.getStringExtra("bookName");
        eBookReadViewModule.refresh(bookId);
        binding.get().pager.setPageTurningListener(new PageTurningListener() {
            @Override
            public void onNext() {
                eBookReadViewModule.loadMore("");
            }

            @Override
            public void onForword() {

            }
        });
//        binding.get().viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if (adapter.get().getCount() - position <= 2) {
//                    eBookReadViewModule.loadMore("");
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }

    @Override
    public void retry() {

    }

    @Override
    public void refreshData(Object data) {
        if (data instanceof List) {

            eBookReadViewModule.loadChapter(((List<BookChapter.MixToc.Chapters>) data).get(0).getLink());
        } else if (data instanceof ChapterRead) {
            binding.get().setPager((ChapterRead) data);
            binding.get().executePendingBindings();
//            LogUtils.d(list.size());
//            adapter.get().setList(list);
        }
    }

    @Override
    public void loadMoreData(Object data) {
        if (data instanceof ChapterRead) {
            List<ChapterPager> list = ReadUtils.getPagers(((ChapterRead) data).getChapter());
            LogUtils.d(list.size());
//            adapter.get().setList(list);
        }
    }
}
