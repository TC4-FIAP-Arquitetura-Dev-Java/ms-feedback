package com.ms.feedback.entrypoint.controllers.presenter;

import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.entrypoint.controllers.mappers.FeedbackDtoMapper;
import com.ms.loginDomain.gen.model.FeedbackRequestDto;
import com.ms.loginDomain.gen.model.FeedbackResponseDto;

public class FeedbackPresenter {

    public static FeedbackDomain toFeedbackDomain(FeedbackRequestDto feedbackRequestDto) {
        return FeedbackDtoMapper.INSTANCE.toFeedbackDomain(feedbackRequestDto);
    }

    public static FeedbackResponseDto toFeedbackRequestDto(FeedbackDomain feedbackDomain) {
        return FeedbackDtoMapper.INSTANCE.toFeedbackResponseDto(feedbackDomain);
    }
}
