package com.nick.sampleroom.utils.binding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class ImageBindingAdapter {
    @BindingAdapter("imageDrawable")
    public static void loadImageBinding(ImageView view, int drawable) {
        Glide.with(view.getContext())
                .load(drawable)
                .into(view);
    }
}
