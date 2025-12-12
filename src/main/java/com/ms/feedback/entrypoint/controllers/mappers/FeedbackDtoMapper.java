package com.ms.feedback.entrypoint.controllers.mappers;

import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.loginDomain.gen.model.FeedbackRequestDto;
import com.ms.loginDomain.gen.model.FeedbackResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FeedbackDtoMapper {

    FeedbackDtoMapper INSTANCE = Mappers.getMapper(FeedbackDtoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataEnvio", ignore = true)
    FeedbackDomain toFeedbackDomain(FeedbackRequestDto feedbackRequestDto);

    FeedbackResponseDto toFeedbackResponseDto(FeedbackDomain feedbackDomain);
}
