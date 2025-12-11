package com.ms.feedback.entrypoint.controllers.presenter;

import com.ms.feedback.domain.model.FeedbackDomain;

public class FeedbackPresenter {

    public static FeedbackDomain toFeedbackDomain(){
        return new FeedbackDomain();
    }

}
