package com.leonardo.financeiro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "lancamento")
public class Lancamento {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Integer codigo;
	  private String descricao;
	  private LocalDate data;
	  @JsonIgnore
	  @ManyToOne
	  @JoinColumn(name = "codigo_categoria")
	  private Categoria categoria;
	  @Enumerated(EnumType.STRING)
	  private TipoLancamento tipo;
	  private BigDecimal valor;
	
	public Lancamento(String descricao, LocalDate data, Categoria categoria, TipoLancamento tipo, BigDecimal valor) {
		this.descricao = descricao;
		this.data = data;
		this.categoria = categoria;
		this.tipo = tipo;
		this.valor = valor;
	}
	public Lancamento() {
		// TODO Auto-generated constructor stub
	}
	  
	public Integer getCodigo() {
		return codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public LocalDate getData() {
		return data;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public TipoLancamento getTipo() {
		return tipo;
	}
	public BigDecimal getValor() {
		return valor;
	}
}