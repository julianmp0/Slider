package com.slider.slider.adapters;


import java.util.List;

import com.slider.slider.SlideType;
import com.slider.slider.viewholder.ImageSlideViewHolder;

/**
 * @author S.Shahini
 * @since 3/4/18
 */

public abstract class SliderAdapter {
    public abstract int getItemCount();

    public abstract List<String> getList();

    public final SlideType getSlideType(int position) {
        return SlideType.IMAGE;
    }

    public abstract void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder);
}
