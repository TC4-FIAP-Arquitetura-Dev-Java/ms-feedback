package com.ms.feedback.infrastrcture.database.entities;

import com.ms.feedback.domain.enuns.TipoUrgenciaEnum;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@Data
@Document(collection = "usuario")
public class FeedbackDocument {

    @Id
    private String id;

    private String descricao;

    private Integer nota;

    private TipoUrgenciaEnum tipoUrgencia;

    @CreatedDate
    private OffsetDateTime dataEnvio;
}
