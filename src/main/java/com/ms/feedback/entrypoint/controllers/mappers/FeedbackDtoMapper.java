package com.ms.feedback.entrypoint.controllers.mappers;

import com.ms.feedback.domain.enuns.UrgencyTypeEnum;
import com.ms.feedback.domain.model.EvaluationByDayDomain;
import com.ms.feedback.domain.model.EvaluationByUrgencyDomain;
import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.domain.model.FeedbackReportDomain;
import com.ms.loginDomain.gen.model.EvaluationByDayDto;
import com.ms.loginDomain.gen.model.EvaluationByUrgencyDto;
import com.ms.loginDomain.gen.model.FeedbackReportResponseDto;
import com.ms.loginDomain.gen.model.FeedbackRequestDto;
import com.ms.loginDomain.gen.model.FeedbackResponseDto;
import com.ms.loginDomain.gen.model.UrgencyTypeEnumDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FeedbackDtoMapper {

    FeedbackDtoMapper INSTANCE = Mappers.getMapper(FeedbackDtoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sentDate", ignore = true)
    FeedbackDomain toFeedbackDomain(FeedbackRequestDto feedbackRequestDto);

    FeedbackResponseDto toFeedbackResponseDto(FeedbackDomain feedbackDomain);

    List<FeedbackResponseDto> toListFeedbackResponseDto(List<FeedbackDomain> feedbackDomains);

    UrgencyTypeEnum toUrgencyTypeEnum(UrgencyTypeEnumDto dto);

    FeedbackReportResponseDto toFeedbackReportResponseDto(FeedbackReportDomain feedbackReportDomain);

    @Mapping(source = "urgencyTypeEnum", target = "urgency")
    EvaluationByUrgencyDto toEvaluationByUrgencyDto(EvaluationByUrgencyDomain domain);

    EvaluationByDayDto toEvaluationByDayDto(EvaluationByDayDomain domain);

    default EvaluationByUrgencyDto.UrgencyEnum map(UrgencyTypeEnum urgencyTypeEnum) {
        if (urgencyTypeEnum == null) {
            return null;
        }
        return EvaluationByUrgencyDto.UrgencyEnum.valueOf(urgencyTypeEnum.name());
    }
}
