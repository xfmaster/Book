package com.xf.oschina.module.story.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xf.oschina.R;
import com.xf.oschina.databinding.BookTextBinding;
import com.xf.oschina.module.story.domain.ChapterPager;
import com.xf.oschina.module.story.module.EBookReadViewModule;
import com.xf.oschina.utils.LogUtils;
import com.xf.oschina.utils.ReadUtils;
import com.xf.oschina.utils.ScreenUtils;
import com.xf.oschina.view.book.BookView;

import java.util.ArrayList;
import java.util.List;

public class BookReadAdapter extends PagerAdapter {
    private List<ChapterPager> list = new ArrayList<>();
    private EBookReadViewModule ebookViewModule;
    private ViewPager viewPager;

    public BookReadAdapter(EBookReadViewModule ebookViewModule, ViewPager viewPager) {
        this.ebookViewModule = ebookViewModule;
        this.viewPager = viewPager;
    }

    public void setList(List<ChapterPager> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        BookTextBinding binding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext())
                , R.layout.book_text, container, false);
        binding.setPager(list.get(position));

//        ((BookView) binding.getRoot()).setController(new BookView.BookViewController() {
//            @Override
//            public void showMenu() {
//
//            }
//
//            @Override
//            public void next() {
//                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
//                int measuredHeight = binding.getRoot().getMeasuredHeight();
//                LogUtils.d(measuredHeight + ">>>" + ScreenUtils.getScreenHeight(binding.getRoot().getContext()));
//            }
//
//            @Override
//            public void foward() {
//                if (viewPager.getCurrentItem() >= 1)
//                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
//            }
//        });
//        int lineHeight = ((BookView) binding.getRoot()).getLineHeight();//
//        LogUtils.d(lineHeight + ">>>>lineHeight>>>>lincount" + ((BookView) binding.getRoot()).getLineCount());
//            float lineSpacingExtra = 1.0f;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                lineSpacingExtra = ((BookView) binding.getRoot()).getLineSpacingExtra();// 行间距
//            }
//        ReadUtils.setTextLineHeight(lineHeight);
//            LogUtils.d(lineHeight);
//        TextView textView = new TextView(container.getContext());
//        textView.setTextSize(16);
//        textView.setText(list.get(position).getBody());
        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
