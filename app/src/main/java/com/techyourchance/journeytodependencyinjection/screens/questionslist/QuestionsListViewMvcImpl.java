package com.techyourchance.journeytodependencyinjection.screens.questionslist;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techyourchance.journeytodependencyinjection.R;
import com.techyourchance.journeytodependencyinjection.questions.Question;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.BaseViewMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of MVC View Interface to notify the controller {@link QuestionsListActivity} of input events.
 */
public class QuestionsListViewMvcImpl extends BaseViewMvc<QuestionsListViewMvc.Listener> implements QuestionsListViewMvc {

    private RecyclerView mRecyclerView;
    private QuestionsAdapter mQuestionsAdapter;

    public QuestionsListViewMvcImpl(LayoutInflater layoutInflater, ViewGroup container) {
        setRootView(layoutInflater.inflate(R.layout.layout_questions_list, container, false));

        // init recycler view
        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mQuestionsAdapter = new QuestionsAdapter(new OnQuestionClickListener() {
            @Override
            public void onQuestionClicked(Question question) {
                for (Listener listener : getListeners()) {
                    listener.onQuestionClicked(question);
                }
            }
        });

        mRecyclerView.setAdapter(mQuestionsAdapter);
    }

    /**
     * Method that binds a list of questions to be displayed, to the View.
     *
     * @param questions List of Questions of instance {@link Question} to be bound to the View.
     */
    @Override
    public void bindQuestions(List<Question> questions) {
        mQuestionsAdapter.bindData(questions);
    }

    // --------------------------------------------------------------------------------------------
    // RecyclerView adapter
    // --------------------------------------------------------------------------------------------


    public interface OnQuestionClickListener {
        void onQuestionClicked(Question question);
    }

    public static class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder> {

        private final OnQuestionClickListener mOnQuestionClickListener;

        private List<Question> mQuestionsList = new ArrayList<>(0);

        public QuestionsAdapter(OnQuestionClickListener onQuestionClickListener) {
            mOnQuestionClickListener = onQuestionClickListener;
        }

        public void bindData(List<Question> questions) {
            mQuestionsList = new ArrayList<>(questions);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_question_list_item, parent, false);

            return new QuestionViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
            holder.mTitle.setText(mQuestionsList.get(position).getTitle());
        }

        @Override
        public int getItemCount() {
            return mQuestionsList.size();
        }

        public class QuestionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView mTitle;

            public QuestionViewHolder(View itemView) {
                super(itemView);
                mTitle = itemView.findViewById(R.id.txt_title);

                itemView.setOnClickListener(this);
            }

            /**
             * Called when a view has been clicked.
             *
             * @param view The view that was clicked.
             */
            @Override
            public void onClick(View view) {
                int adapterPosition = getAdapterPosition();
                if (adapterPosition > RecyclerView.NO_POSITION) {
                    Question itemQuestion = mQuestionsList.get(adapterPosition);
                    int clickedViewId = view.getId();

                    if (clickedViewId == itemView.getId()) {
                        mOnQuestionClickListener.onQuestionClicked(itemQuestion);
                    }
                }
            }
        }
    }
}
