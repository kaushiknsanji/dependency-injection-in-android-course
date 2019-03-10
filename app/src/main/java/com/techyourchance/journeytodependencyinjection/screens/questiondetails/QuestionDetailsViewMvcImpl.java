package com.techyourchance.journeytodependencyinjection.screens.questiondetails;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.techyourchance.journeytodependencyinjection.R;
import com.techyourchance.journeytodependencyinjection.questions.QuestionDetails;
import com.techyourchance.journeytodependencyinjection.screens.common.ImageLoader;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.BaseViewMvc;

/**
 * Implementation of the MVC View Interface to notify the controller {@link QuestionDetailsActivity}
 * of input events.
 */
public class QuestionDetailsViewMvcImpl extends BaseViewMvc<QuestionDetailsViewMvc.Listener>
        implements QuestionDetailsViewMvc {

    private final ImageLoader mImageLoader;
    private final TextView mTxtUserDisplayName;
    private final ImageView mImgUserAvatar;
    private TextView mTxtQuestionBody;
    private FrameLayout mFrameFragment;

    public QuestionDetailsViewMvcImpl(LayoutInflater layoutInflater, ViewGroup container, ImageLoader imageLoader) {
        setRootView(layoutInflater.inflate(R.layout.layout_question_details, container, false));
        mImageLoader = imageLoader;

        mTxtQuestionBody = findViewById(R.id.txt_question_body);
        mTxtUserDisplayName = findViewById(R.id.txt_user_display_name);
        mImgUserAvatar = findViewById(R.id.img_user_avatar);
        mFrameFragment = findViewById(R.id.frame_fragment);
    }

    /**
     * Method that binds the {@link QuestionDetails} data to the View.
     *
     * @param question Instance of {@link QuestionDetails}
     */
    @Override
    public void bindQuestion(QuestionDetails question) {
        String questionBody = question.getBody();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            mTxtQuestionBody.setText(Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY));
        } else {
            mTxtQuestionBody.setText(Html.fromHtml(questionBody));
        }

        mTxtUserDisplayName.setText(question.getUserDisplayName());
        mImageLoader.loadImage(question.getUserAvatarUrl(), mImgUserAvatar);
    }

    /**
     * Method that returns the container view 'R.id.frame_fragment' for Fragments
     *
     * @return Instance of {@link FrameLayout} which is a container view for Fragments
     */
    @Override
    public FrameLayout getFrameFragment() {
        return mFrameFragment;
    }
}
