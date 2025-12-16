package com.ms.feedback.entrypoint.controllers.filter;

import com.ms.feedback.domain.enuns.TipoUrgenciaEnum;

public record FeedbackFilter(
        String descricao,
        TipoUrgenciaEnum tipoUrgencia,
        Integer limit,
        Integer offset
) {}
