package com.leonardo.financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.leonardo.financeiro.model.Lancamento;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> , JpaSpecificationExecutor<Lancamento> {

	@Query("SELECT SUM(l.valor) FROM Lancamento l WHERE l.tipo = 'RECEITA' GROUP BY l.tipo")
	String totalReceitas();
	
	@Query("SELECT SUM(l.valor) FROM Lancamento l WHERE l.tipo = 'DESPESA' GROUP BY l.tipo")
	String totalDespesas();
    
	@Query("SELECT l FROM Lancamento l INNER JOIN Categoria c ON l.categoria = c.codigo WHERE c.nome = ?1")
	List<Lancamento> findByCategoria(String nome);

}