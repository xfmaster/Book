package com.xf.oschina.module.story.adapter;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xf.oschina.R;
import com.xf.oschina.common.DataBoundListAdapter;
import com.xf.oschina.databinding.ItemEbookListBinding;
import com.xf.oschina.module.story.domain.BooksBean;

public class EbookListAdapter extends DataBoundListAdapter<BooksBean, ItemEbookListBinding> {

    @Override
    protected ItemEbookListBinding createBinding(ViewGroup parent) {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_ebook_list, parent, false);
    }

    @Override
    protected void bind(ItemEbookListBinding binding, BooksBean item) {
        binding.setBook(item);
    }

    @Override
    protected boolean areItemsTheSame(BooksBean oldItem, BooksBean newItem) {
        return oldItem.get_id().equals(newItem.get_id());
    }

    @Override
    protected boolean areContentsTheSame(BooksBean oldItem, BooksBean newItem) {
        return oldItem.getTitle().equals(newItem.getTitle());
    }
}
