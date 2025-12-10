package com.ms.feedback.domain.model;

import com.ms.feedback.domain.enuns.TipoUrgenciaEnum;

import java.time.OffsetDateTime;

public class FeedbackDomain {

    private String id;
    private String descricao;
    private Integer nota;
    private TipoUrgenciaEnum tipoUrgencia;
    private OffsetDateTime dataEnvio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public TipoUrgenciaEnum getTipoUrgencia() {
        return tipoUrgencia;
    }

    public void setTipoUrgencia(TipoUrgenciaEnum tipoUrgencia) {
        this.tipoUrgencia = tipoUrgencia;
    }

    public OffsetDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(OffsetDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}
