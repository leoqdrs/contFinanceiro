package com.leonardo.financeiro.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.leonardo.financeiro.model.TipoLancamento;


public class LancamentoDto {

	  private String descricao;
	  private LocalDate data;
	  private Integer categoriaId;
	  @Enumerated(EnumType.STRING)
	  private TipoLancamento tipo;
	  private BigDecimal valor;
	
	public LancamentoDto(String descricao, LocalDate data, Integer categoriaId, TipoLancamento tipo, BigDecimal valor) {
		this.descricao = descricao;
		this.data = data;
		this.categoriaId = categoriaId;
		this.tipo = tipo;
		this.valor = valor;
	}
	
	public LancamentoDto() {
		// TODO Auto-generated constructor stub
	}
	  
	public String getDescricao() {
		return descricao;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public Integer getCategoriaId() {
		return categoriaId;
	}
	
	public TipoLancamento getTipo() {
		return tipo;
	}
	
	public BigDecimal getValor() {
		return valor;
	}

}