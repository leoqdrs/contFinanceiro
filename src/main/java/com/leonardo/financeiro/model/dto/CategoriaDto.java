package com.leonardo.financeiro.model.dto;

import java.math.BigDecimal;

import com.leonardo.financeiro.model.Categoria;

public class CategoriaDto {

  private String nome;
  private BigDecimal limiteMensal;
  
  public CategoriaDto(String nome, BigDecimal limiteMensal) {
	this.nome = nome;
	this.limiteMensal = limiteMensal;
  }
  
  public CategoriaDto() {
	// TODO Auto-generated constructor stub
  }
  
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public BigDecimal getLimiteMensal() {
	return limiteMensal;
  }
  
  public void setLimiteMensal(BigDecimal limiteMensal) {
	this.limiteMensal = limiteMensal;
  }

public Categoria getCategoria() {
	Categoria categoria = new Categoria(nome, limiteMensal);
	return categoria;
}

}