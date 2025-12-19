package com.ms.feedback.entrypoint.controllers.filter;

import com.ms.feedback.domain.enuns.TipoUrgenciaEnum;

public class FeedbackFilter{

    private String descricao;
    private TipoUrgenciaEnum tipoUrgencia;
    private Integer limit;
    private Integer offset;

    public FeedbackFilter() {
    }

    public FeedbackFilter(String descricao, TipoUrgenciaEnum tipoUrgencia, Integer limit, Integer offset) {
        this.descricao = descricao;
        this.tipoUrgencia = tipoUrgencia;
        this.limit = limit;
        this.offset = offset;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoUrgenciaEnum getTipoUrgencia() {
        return tipoUrgencia;
    }

    public void setTipoUrgencia(TipoUrgenciaEnum tipoUrgencia) {
        this.tipoUrgencia = tipoUrgencia;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
