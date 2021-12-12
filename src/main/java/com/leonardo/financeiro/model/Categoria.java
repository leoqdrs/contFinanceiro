package com.leonardo.financeiro.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer codigo;
  private String nome;
  private BigDecimal limite;
  
  @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
  private List<Lancamento> lancamentos = new ArrayList<>();
  
  public Categoria(String nome, BigDecimal limite) {
	this.nome = nome;
	this.limite = limite;
  }
  
  public Categoria() {
	// TODO Auto-generated constructor stub
  }
  
  public Integer getCodigo() {
    return codigo;
  }

  public String getNome() {
    return nome;
  }
  
  public BigDecimal getLimite() {
	return limite;
  }
  
  public List<Lancamento> getLancamentos() {
	return lancamentos;
  }

}