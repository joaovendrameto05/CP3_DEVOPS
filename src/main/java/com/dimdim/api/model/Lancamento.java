package com.dimdim.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "lancamento")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description; // Mapeia a coluna descricao na tabela
    
    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor")
    private BigDecimal valor;

    // Getters e Setters (Obrigatórios para o Jackson preencher o JSON)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor; ) { this.valor = valor; }
}