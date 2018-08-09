package com.xf.oschina.module.book.adapter;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xf.oschina.R;
import com.xf.oschina.common.DataBoundListAdapter;
import com.xf.oschina.databinding.ItemBookBinding;
import com.xf.oschina.module.book.domain.Book;

public class BookListAdapter extends DataBoundListAdapter<Book, ItemBookBinding> {


    @Override
    protected ItemBookBinding createBinding(ViewGroup parent) {
        ItemBookBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_book, parent, false);
        return binding;
    }

    @Override
    protected void bind(ItemBookBinding binding, Book item) {
        binding.setBook(item);
    }

    @Override
    protected boolean areItemsTheSame(Book oldItem, Book newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    protected boolean areContentsTheSame(Book oldItem, Book newItem) {
        return oldItem.getTitle().equals(newItem.getTitle());
    }
}
