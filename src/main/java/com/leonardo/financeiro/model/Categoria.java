package com.leonardo.financeiro.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer codigo;
  private String nome;
  
  public Categoria(String nome) {
	this.nome = nome;
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

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Categoria)) {
      return false;
    }
    Categoria categoria = (Categoria) o;
    return Objects.equals(codigo, categoria.codigo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo);
  }

}