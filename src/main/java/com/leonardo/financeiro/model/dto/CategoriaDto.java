package com.leonardo.financeiro.model.dto;

import java.math.BigDecimal;

import com.leonardo.financeiro.model.Categoria;

public class CategoriaDto {

  private String nome;
  private BigDecimal limite;
  
  public CategoriaDto(String nome, BigDecimal limite) {
	this.nome = nome;
	this.limite = limite;
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

  public BigDecimal getLimite() {
	return limite;
  }
  
  public void setLimite(BigDecimal limite) {
	this.limite = limite;
  }

public Categoria getCategoria() {
	Categoria categoria = new Categoria(nome, limite);
	return categoria;
}

}