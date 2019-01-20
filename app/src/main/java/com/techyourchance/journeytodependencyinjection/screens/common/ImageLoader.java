package com.techyourchance.journeytodependencyinjection.screens.common;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Class that downloads Image from the given remote URI and loads it onto the target ImageView passed.
 */
public class ImageLoader {

    //Instance of Activity
    private final Activity mActivity;

    //Instance of RequestOptions to customize Glide
    private final RequestOptions mDefaultRequestOptions;

    /**
     * Constructor of {@link ImageLoader}
     *
     * @param activity Instance of {@link Activity} required by Glide
     */
    public ImageLoader(Activity activity) {
        mActivity = activity;
        //Using Glide with Center Crop
        mDefaultRequestOptions = new RequestOptions().centerCrop();
    }

    /**
     * Method that downloads Image from the given {@code uri} and sets it on to the
     * {@code target} ImageView.
     *
     * @param uri    The String URI to download the Image from.
     * @param target The Target ImageView on which the Image needs to be set
     */
    public void loadImage(String uri, ImageView target) {
        Glide.with(mActivity)
                .load(uri)
                .apply(mDefaultRequestOptions)
                .into(target);
    }
}
