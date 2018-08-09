package com.xf.oschina.module.home.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xf.oschina.R;
import com.xf.oschina.base.BaseFragment;
import com.xf.oschina.databinding.FragmentMovieBinding;

public class MovieFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentMovieBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false);
        return binding.getRoot();
    }

    @Override
    public void retry() {

    }

    @Override
    public void refresh() {

    }
}
