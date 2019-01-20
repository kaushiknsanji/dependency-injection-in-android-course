package com.techyourchance.journeytodependencyinjection.screens.common.mvcviews;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.techyourchance.journeytodependencyinjection.screens.common.ImageLoader;
import com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsViewMvc;
import com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsViewMvcImpl;
import com.techyourchance.journeytodependencyinjection.screens.questionslist.QuestionsListViewMvc;
import com.techyourchance.journeytodependencyinjection.screens.questionslist.QuestionsListViewMvcImpl;

/**
 * Factory class for providing MVC Views
 */
public class ViewMvcFactory {

    //LayoutInflater instance to inflate views
    private final LayoutInflater mLayoutInflater;

    //Instance of ImageLoader to download images
    private final ImageLoader mImageLoader;

    /**
     * Constructor of {@link ViewMvcFactory}
     *
     * @param layoutInflater Instance of {@link LayoutInflater} for inflating the views
     * @param imageLoader Instance of {@link ImageLoader} to download images
     */
    public ViewMvcFactory(LayoutInflater layoutInflater, ImageLoader imageLoader) {
        mLayoutInflater = layoutInflater;
        mImageLoader = imageLoader;
    }

    /**
     * Factory Method that instantiates a new implementation of MVC View. The returned instance
     * is auto type-inferred to the respective MVC View type.
     *
     * @param mvcViewClass The Class of the required MVC View
     * @param container    The {@link ViewGroup} to be used as the MVC View's parent.
     * @param <T>          The Type of the required MVC View
     * @return New instance of the MVC View
     */
    public <T extends ViewMvc> T newInstance(Class<T> mvcViewClass, @Nullable ViewGroup container) {
        ViewMvc viewMvc;

        if (mvcViewClass == QuestionsListViewMvc.class) {
            viewMvc = new QuestionsListViewMvcImpl(mLayoutInflater, container);
        } else if (mvcViewClass == QuestionDetailsViewMvc.class) {
            viewMvc = new QuestionDetailsViewMvcImpl(mLayoutInflater, container, mImageLoader);
        } else {
            throw new IllegalArgumentException("Unsupported MVC view class " + mvcViewClass);
        }

        //noinspection unchecked
        return (T) viewMvc;
    }
}
