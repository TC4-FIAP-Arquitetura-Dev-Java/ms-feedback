package com.ms.feedback.domain.enuns;

public enum TipoUrgenciaEnum {

    BAIXA("Baixa", 1),
    MEDIA("Média", 2),
    ALTA("Alta", 3),
    URGENTE("Urgente", 4);

    private final String descricao;
    private final int nivel;

    TipoUrgenciaEnum(String descricao, int nivel) {
        this.descricao = descricao;
        this.nivel = nivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getNivel() {
        return nivel;
    }
}

