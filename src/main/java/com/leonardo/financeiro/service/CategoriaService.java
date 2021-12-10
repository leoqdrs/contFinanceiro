package com.leonardo.financeiro.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.financeiro.model.Categoria;
import com.leonardo.financeiro.model.dto.CategoriaDto;
import com.leonardo.financeiro.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private final CategoriaRepository categoriaRepository;

	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}
	
	public Categoria salvar(CategoriaDto categoriaDto) {
		Categoria categoria = categoriaDto.getCategoria();
		return categoriaRepository.save(categoria);
	}

	public Categoria buscarPorId(Integer categoriaId) {
		return categoriaRepository.findById(categoriaId)
				.orElseThrow(() -> new EntityNotFoundException("Categoria com id " + categoriaId + " não encontrado"));
	}

	public List<Categoria> buscarCategorias() {
		return categoriaRepository.findAll();
	}

}
