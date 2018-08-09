package com.xf.oschina.module.story.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xf.oschina.R;
import com.xf.oschina.common.DataBoundListAdapter;
import com.xf.oschina.databinding.ItemBookDetailCommentBinding;
import com.xf.oschina.databinding.ItemEbookDetailBinding;
import com.xf.oschina.module.story.domain.EBookComment;

public class EBookDetailAdapter extends DataBoundListAdapter<EBookComment, ItemEbookDetailBinding> {
    @Override
    protected ItemEbookDetailBinding createBinding(ViewGroup parent) {
        ItemEbookDetailBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_ebook_detail, parent, false);
        return dataBinding;
    }

    @Override
    protected void bind(ItemEbookDetailBinding binding, EBookComment item) {
        binding.setComment(item);
    }

    @Override
    protected boolean areItemsTheSame(EBookComment oldItem, EBookComment newItem) {
        return oldItem.get_id().equals(newItem.get_id());
    }

    @Override
    protected boolean areContentsTheSame(EBookComment oldItem, EBookComment newItem) {
        return oldItem.getContent().equals(newItem.getContent());
    }
}
