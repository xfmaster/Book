package com.xf.oschina.module.book.adapter;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xf.oschina.R;
import com.xf.oschina.common.DataBoundListAdapter;
import com.xf.oschina.databinding.ItemBookDetailCommentBinding;
import com.xf.oschina.module.book.domain.Comment;

public class CommentAdatpter extends DataBoundListAdapter<Comment, ItemBookDetailCommentBinding> {
    @Override
    protected ItemBookDetailCommentBinding createBinding(ViewGroup parent) {
        ItemBookDetailCommentBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_book_detail_comment, parent, false);
        return binding;
    }

    @Override
    protected void bind(ItemBookDetailCommentBinding binding, Comment item) {
        binding.setComment(item);
    }

    @Override
    protected boolean areItemsTheSame(Comment oldItem, Comment newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    protected boolean areContentsTheSame(Comment oldItem, Comment newItem) {
        return oldItem.getSummary().equals(newItem.getSummary());
    }
}
