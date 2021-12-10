package com.leonardo.financeiro.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.financeiro.model.Categoria;
import com.leonardo.financeiro.model.Lancamento;
import com.leonardo.financeiro.model.dto.LancamentoDto;
import com.leonardo.financeiro.repository.CategoriaRepository;
import com.leonardo.financeiro.repository.LancamentoRepository;

@Service
public class LancamentoService {
	
	@Autowired
	private final LancamentoRepository lancamentoRepository;
	
	@Autowired
	private final CategoriaRepository categoriaRepository;

	public LancamentoService(LancamentoRepository lancamentoRepository,CategoriaRepository categoriaRepository) {
		this.lancamentoRepository = lancamentoRepository;
		this.categoriaRepository = categoriaRepository;
	}
	
	public Lancamento salvar(LancamentoDto lancamentoDto) {
		Categoria categoria = categoriaRepository.getById(lancamentoDto.getCategoria());
		Lancamento lancamento = new Lancamento(lancamentoDto.getDescricao(), lancamentoDto.getData(), categoria, lancamentoDto.getTipo(),lancamentoDto.getValor());
		return lancamentoRepository.save(lancamento);
	}

	public Lancamento buscarPorId(Integer lancamentoId) {
		return lancamentoRepository.findById(lancamentoId)
				.orElseThrow(() -> new EntityNotFoundException("Lancamento com id " + lancamentoId + " não encontrado"));
	}

	public List<Lancamento> buscarLancamentos() {
		return lancamentoRepository.findAll();
	}

}
