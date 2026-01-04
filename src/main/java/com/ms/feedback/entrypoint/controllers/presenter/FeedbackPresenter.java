package com.ms.feedback.entrypoint.controllers.presenter;

import com.ms.feedback.domain.enuns.UrgencyTypeEnum;
import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.domain.model.FeedbackReportDomain;
import com.ms.feedback.entrypoint.controllers.mappers.FeedbackDtoMapper;
import com.ms.loginDomain.gen.model.FeedbackReportResponseDto;
import com.ms.loginDomain.gen.model.FeedbackRequestDto;
import com.ms.loginDomain.gen.model.FeedbackResponseDto;
import com.ms.loginDomain.gen.model.UrgencyTypeEnumDto;

import java.util.List;

public class FeedbackPresenter {

    public static FeedbackDomain toFeedbackDomain(FeedbackRequestDto feedbackRequestDto) {
        return FeedbackDtoMapper.INSTANCE.toFeedbackDomain(feedbackRequestDto);
    }

    public static FeedbackResponseDto toFeedbackResponseDto(FeedbackDomain feedbackDomain) {
        return FeedbackDtoMapper.INSTANCE.toFeedbackResponseDto(feedbackDomain);
    }

    public static FeedbackReportResponseDto toFeedbackReportResponseDto(FeedbackReportDomain feedbackDomain) {
        return FeedbackDtoMapper.INSTANCE.toFeedbackReportResponseDto(feedbackDomain);
    }

    public static List<FeedbackResponseDto> toListFeedbackResponseDto(List<FeedbackDomain> feedbackDomains) {
        return FeedbackDtoMapper.INSTANCE.toListFeedbackResponseDto(feedbackDomains);
    }

    public static UrgencyTypeEnum toUrgencyTypeEnum(UrgencyTypeEnumDto dto) {
        return FeedbackDtoMapper.INSTANCE.toUrgencyTypeEnum(dto);
    }
}
