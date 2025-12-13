package com.ms.feedback.application.dto;

import com.ms.feedback.domain.enuns.TipoUrgenciaEnum;

public record FeedbackFilter(String descricao,
                             TipoUrgenciaEnum tipoUrgenciaEnum) {
}
