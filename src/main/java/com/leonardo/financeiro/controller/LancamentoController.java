package com.leonardo.financeiro.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.financeiro.model.Lancamento;
import com.leonardo.financeiro.model.dto.LancamentoDto;
import com.leonardo.financeiro.service.CategoriaService;
import com.leonardo.financeiro.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {
	
    @Autowired
    LancamentoService lancamentoService;
    
    @Autowired
    CategoriaService categoriaService;

	public LancamentoController(LancamentoService lancamentoservice) {
		this.lancamentoService = lancamentoservice;
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<?> salvar(@RequestBody LancamentoDto lancamentoDto) {
		BigDecimal limiteMensal = categoriaService.buscarLimite(lancamentoDto.getCategoriaId());
		if (limiteMensal.compareTo(lancamentoDto.getValor()) < 0) {
			return ResponseEntity.badRequest().body("Limite mensal excedido");
		}	
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
	
    @PutMapping
    public ResponseEntity<Lancamento> alteraLancamento(@RequestBody Lancamento lancamento)  {
        Lancamento retLancamento = lancamentoService.alteraLancamento(lancamento);

        if (retLancamento != null){
            return ResponseEntity.ok(retLancamento);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaLancamento(@PathVariable Integer id)  {
        lancamentoService.deletaLancamento(id);
        return ResponseEntity.noContent().build();
    }
    
	@GetMapping("/balanco")
	public List<String> listarLancamentos(){
		return lancamentoService.listarLancamentos();
	}
	
	@GetMapping("/categoria/{nome}")
	public List<Lancamento> listarLancamentosporCategoria(@PathVariable String nome){
		return lancamentoService.listarLancamentosporCategoria(nome);
	}
	
	@GetMapping("/data/{valor}")
	public List<Lancamento> listarLancamentosporData(@PathVariable String valor){
		return lancamentoService.listarLancamentosporData(valor);
	}

	@GetMapping("/dia/{valor}")
	public List<Lancamento> listarLancamentosporDia(@PathVariable String valor){
		return lancamentoService.listarLancamentosporDia(valor);
	}
	
	@GetMapping("/semana/{valor}")
	public List<Lancamento> listarLancamentosporSemana(@PathVariable String valor){
		return lancamentoService.listarLancamentosporSemana(valor);
	}
	
	@GetMapping("/mes/{valor}")
	public List<Lancamento> listarLancamentosporMes(@PathVariable String valor){
		return lancamentoService.listarLancamentosporMes(valor);
	}
	
}
