package com.xf.oschina.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.xf.oschina.R;
import com.xf.oschina.constants.ImageOption;
import com.xf.oschina.listener.RetryCallback;
import com.xf.oschina.utils.ScreenUtils;

/**
 * @author xf
 * @time 2018/7/9  17:41
 * @describe 加载数据
 */
public class LoadingView extends FrameLayout {
    private TextView tv_msg;
    private RetryCallback callback;

    public LoadingView(@NonNull Context context) {
        super(context);
        initeLayout(context);
    }
    public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initeLayout(context);
    }
    public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initeLayout(context);
    }


    @Nullable
    private void initeLayout(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.loading, null);
        ImageView iv_loading = view.findViewById(R.id.loading);
        tv_msg = view.findViewById(R.id.tv_message);
        Glide.with(view.getContext()).load(R.drawable.loading).
                thumbnail(0.2f).apply(ImageOption.commonImageOption()).into(iv_loading);
        addView(view);
    }

    public void setMessage(String msg) {
        tv_msg.setText(msg);
    }


    public void setCallback(RetryCallback callback) {
        this.callback = callback;
    }
}
