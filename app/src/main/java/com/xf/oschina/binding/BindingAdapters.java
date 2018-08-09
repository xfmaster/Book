/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xf.oschina.binding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.xf.oschina.constants.ImageOption;
import com.xf.oschina.view.book.Pager;

/**
 * Data Binding adapters specific to the app.
 */
public class BindingAdapters {
    private static final String TAG = BindingAdapters.class.getSimpleName();

    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("image")
    public static void loadImage(ImageView view, String imageUrl) {
        Log.d(TAG, imageUrl + "");
        if (imageUrl.contains("agent")) {
            imageUrl = "http://statics.zhuishushenqi.com" + imageUrl;
            Glide.with(view.getContext())
                    .load(imageUrl)
                    .into(view);
        } else
            Glide.with(view.getContext()).load(imageUrl).thumbnail(0.2f).apply(ImageOption.commonImageOption()).
                    into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            view.setImageDrawable(resource);
                        }
                    });
    }

    @BindingAdapter("imageCircle")
    public static void loadCircleImage(ImageView view, String imageUrl) {
        Log.d(TAG, imageUrl + "");
        if (!imageUrl.contains("http")) {
            imageUrl = "http://statics.zhuishushenqi.com" + imageUrl;
        }
        Glide.with(view.getContext()).load(imageUrl).thumbnail(0.2f).apply(ImageOption.CicleImageOption()).thumbnail(0.2f)
                .into(view);
    }

    @BindingAdapter(value = {"text"})
    public static void setText(Pager pager, String text) {
        pager.setText(text);
    }
}
