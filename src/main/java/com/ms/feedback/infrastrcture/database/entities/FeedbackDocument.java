package com.ms.feedback.infrastrcture.database.entities;

import com.ms.feedback.domain.enuns.TipoUrgenciaEnum;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Data
@Document(collection = "feedback")
public class FeedbackDocument {

    @Id
    private String id;

    private String descricao;

    private Integer nota;

    @Field("tipoUrgencia")
    private TipoUrgenciaEnum tipoUrgenciaEnum;

    @CreatedDate
    private Instant dataEnvio;
}
