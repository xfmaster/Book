package com.xf.oschina.constants;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class ImageOption {
    public static RequestOptions CicleImageOption() {
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        requestOptions.fitCenter();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        return requestOptions;
    }
    public static RequestOptions commonImageOption() {
        RequestOptions requestOptions = RequestOptions.centerCropTransform();
        requestOptions.fitCenter();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        return requestOptions;
    }
}
