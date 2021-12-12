package com.leonardo.financeiro.service;

import java.util.ArrayList;
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
		Categoria categoria = categoriaRepository.getById(lancamentoDto.getCategoriaId());
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
	
    public Lancamento alteraLancamento(Lancamento lancamento) {
        Lancamento retornoLancamento = lancamentoRepository.findById(lancamento.getCodigo()).orElseThrow(() -> new EntityNotFoundException("Lancamento de id " + lancamento.getCodigo() + " não encontrado"));
        if (retornoLancamento != null) {
            return lancamentoRepository.save(lancamento);
        }
        return lancamento;
    }


    public void deletaLancamento(Integer id) {
        Lancamento retornoLancamento = lancamentoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Lancamento de id " + id + " não encontrado"));
        if (retornoLancamento != null) {
            lancamentoRepository.deleteById(retornoLancamento.getCodigo());
        }
    }

	public List<String> listarLancamentos() {
		List<String> resultado = new ArrayList<String>();
		String totalReceitas = ("Total de Receitas: " + lancamentoRepository.totalReceitas());
		String totalDespesas = ("Total de Despesas: " + lancamentoRepository.totalDespesas());
		resultado.add(totalReceitas);
		resultado.add(totalDespesas);
		return resultado;
		}

	public List<Lancamento> listarLancamentosporCategoria(String nome) {
		List<Lancamento> resultado = lancamentoRepository.findByCategoria(nome);
		return resultado;
	}

}
