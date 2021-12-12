package com.leonardo.financeiro.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.leonardo.financeiro.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> , JpaSpecificationExecutor<Categoria> {

	@Query("SELECT c.limite FROM Categoria c WHERE c.codigo = ?1")
	BigDecimal getLimite(Integer id);
	
}
