package com.techyourchance.journeytodependencyinjection.common.dependencyinjection;

import com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity;
import com.techyourchance.journeytodependencyinjection.screens.questionslist.QuestionsListActivity;

/**
 * Wrapper Class of {@link PresentationCompositionRoot} to inject required dependencies into the clients.
 */
public class Injector {

    //Instance of PresentationCompositionRoot for getting the services
    private final PresentationCompositionRoot mPresentationCompositionRoot;

    /**
     * Constructor of {@link Injector}
     *
     * @param presentationCompositionRoot Instance of {@link PresentationCompositionRoot} for getting the services
     */
    public Injector(PresentationCompositionRoot presentationCompositionRoot) {
        mPresentationCompositionRoot = presentationCompositionRoot;
    }

    /**
     * Method that injects dependencies into the clients
     *
     * @param client An {@link android.support.v7.app.AppCompatActivity} that requires
     *               the services to be injected
     */
    public void inject(Object client) {
        if (client instanceof QuestionsListActivity) {
            injectQuestionsListActivity((QuestionsListActivity) client);
        } else if (client instanceof QuestionDetailsActivity) {
            injectQuestionDetailsActivity((QuestionDetailsActivity) client);
        } else {
            throw new RuntimeException("Invalid client: " + client);
        }
    }

    /**
     * Method that injects dependencies for {@link QuestionsListActivity}
     *
     * @param client A {@link QuestionsListActivity} instance
     */
    private void injectQuestionsListActivity(QuestionsListActivity client) {
        client.mViewMvcFactory = mPresentationCompositionRoot.getViewMvcFactory();
        client.mDialogsManager = mPresentationCompositionRoot.getDialogsManager();
        client.mFetchQuestionsListUseCase = mPresentationCompositionRoot.getFetchQuestionsListUseCase();
    }

    /**
     * Method that injects dependencies for {@link QuestionDetailsActivity}
     *
     * @param client A {@link QuestionDetailsActivity} instance
     */
    private void injectQuestionDetailsActivity(QuestionDetailsActivity client) {
        client.mViewMvcFactory = mPresentationCompositionRoot.getViewMvcFactory();
        client.mDialogsManager = mPresentationCompositionRoot.getDialogsManager();
        client.mFetchQuestionDetailsUseCase = mPresentationCompositionRoot.getFetchQuestionDetailsUseCase();
    }
}
