package com.ms.feedback.entrypoint.controllers.mappers;

import com.ms.feedback.application.dto.FeedbackFilter;
import com.ms.loginDomain.gen.model.TipoUrgenciaEnumDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FeedbackFilterMapper {

    FeedbackFilterMapper INSTANCE = Mappers.getMapper(FeedbackFilterMapper.class);

    FeedbackFilter toFilter(String descricao, TipoUrgenciaEnumDto tipoUrgencia, Integer limit, Integer offset);
}
