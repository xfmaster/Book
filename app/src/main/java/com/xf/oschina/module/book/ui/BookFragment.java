package com.xf.oschina.module.book.ui;

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
import com.xf.oschina.databinding.FragmentBookBinding;
import com.xf.oschina.module.book.adapter.BookListAdapter;
import com.xf.oschina.module.book.domain.BookData;
import com.xf.oschina.module.book.module.BookViewModule;
import com.xf.oschina.utils.AutoClearedValue;

import javax.inject.Inject;

public class BookFragment extends BaseFragment {
    AutoClearedValue<FragmentBookBinding> binding;
    @Inject
    BookViewModule bookViewModule;
    AutoClearedValue<BookListAdapter> adapter;
    private String tag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentBookBinding bookBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_book, container, false);
        binding = new AutoClearedValue<>(this, bookBinding, bookViewModule);
        this.tag = getArguments().getString("tag", "hot");
        return bookBinding.getRoot();
    }

    public static BookFragment createBookFragment(String tag, String title, int currentIndex) {
        Bundle bundle = new Bundle();
        bundle.putString("tag", tag);
        bundle.putString("title", title);
        bundle.putInt("currentIndex", currentIndex);
        BookFragment bookFragment = new BookFragment();
        bookFragment.setArguments(bundle);
        return bookFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BookListAdapter bookListAdapter = new BookListAdapter();
        binding.get().reBookList.setAdapter(bookListAdapter);
        adapter = new AutoClearedValue<>(this, bookListAdapter, bookViewModule);
        adapter.get().setOnItemClick(bookViewModule);
        initeRefresh();
        if (getArguments().getInt("currentIndex", 0) == 0) {
            refresh();
        }
    }


    private void initeRefresh() {
        binding.get().refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                bookViewModule.loadMore(tag);

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                bookViewModule.refresh(tag);
            }
        });
    }

    @Override
    public void retry() {
        showLoading();
        bookViewModule.refresh(tag);
    }

    @Override
    public void refresh() {
        showLoading();
        if (bookViewModule != null && binding.get().reBookList.getAdapter().getItemCount() == 0)
            bookViewModule.refresh(tag);
    }

    @Override
    public void loadMoreData(Object data) {
        BookData bookData = (BookData) data;
        adapter.get().apendList(bookData.getBooks() == null ? null : bookData.getBooks());
        binding.get().executePendingBindings();
        binding.get().refresh.finishLoadMore();
    }

    @Override
    public void refreshData(Object data) {
        BookData bookData = (BookData) data;
        adapter.get().refresh(bookData.getBooks() == null ? null : bookData.getBooks());
        binding.get().refresh.finishRefresh();
    }

//    @Override
//    public void showToast(String message) {
//        Snackbar snackbar = Snackbar.make(binding.get().reBookList, message, Snackbar.LENGTH_SHORT);
//        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.common_color));
//        snackbar.show();
//    }
}
