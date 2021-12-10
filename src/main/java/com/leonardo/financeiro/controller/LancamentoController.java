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

import com.leonardo.financeiro.model.Lancamento;
import com.leonardo.financeiro.model.dto.LancamentoDto;
import com.leonardo.financeiro.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {
	
    @Autowired
    LancamentoService lancamentoService;

	public LancamentoController(LancamentoService lancamentoservice) {
		this.lancamentoService = lancamentoservice;
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<?> salvar(@RequestBody LancamentoDto lancamentoDto) {
		Lancamento lancamento = lancamentoService.salvar(lancamentoDto);
		return ResponseEntity.ok(lancamento);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> lancamentos(@PathVariable Integer id){
		Lancamento lancamentos =  lancamentoService.buscarPorId(id);
		return ResponseEntity.ok(lancamentos);
	}
	
	@GetMapping
	public List<Lancamento> buscarLancamentos(){
		return lancamentoService.buscarLancamentos();
	} 
	
}
