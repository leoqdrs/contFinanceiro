package com.leonardo.financeiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.financeiro.model.Categoria;
import com.leonardo.financeiro.model.dto.CategoriaDto;
import com.leonardo.financeiro.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
    @Autowired
    CategoriaService categoriaService;

	public CategoriaController(CategoriaService categoriaservice) {
		this.categoriaService = categoriaservice;
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<?> salvar(@RequestBody CategoriaDto categoriaDto) {
		Categoria categoria = categoriaService.salvar(categoriaDto);
		return ResponseEntity.ok(categoria);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> categorias(@PathVariable Integer id){
		Categoria categorias =  categoriaService.buscarPorId(id);
		return ResponseEntity.ok(categorias);
	}
	
	@GetMapping
	public List<Categoria> buscarCategorias(){
		return categoriaService.buscarCategorias();
	} 

}
