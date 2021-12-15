package com.leonardo.financeiro.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonardo.financeiro.model.Categoria;
import com.leonardo.financeiro.model.Lancamento;
import com.leonardo.financeiro.model.TipoLancamento;
import com.leonardo.financeiro.model.dto.LancamentoDto;
import com.leonardo.financeiro.service.LancamentoService;

@AutoConfigureMockMvc
@WebMvcTest(controllers = {LancamentoController.class})
class LancamentoControllerTest {
	
	private final String url= "/lancamentos";
	private final ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private LancamentoService lancamentoService;
	
	@Test
	void testSalvar() throws Exception {

		doReturn(new Lancamento("Martelo", LocalDate.parse("2021-12-13"), new Categoria("Equipamentos", BigDecimal.valueOf(300.00)), TipoLancamento.DESPESA, BigDecimal.valueOf(300.00))).when(lancamentoService).salvar(Mockito.any());
		String body = "{\"nome\":\"Equipamentos\"}";
		mockMvc
		.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body)).andExpect(status().isCreated());
	}

	@Test
	void deveriaRetornarSucesso200OKQuandoBuscarLancamentoPorId() throws Exception {
		
		when(lancamentoService.buscarPorId(1))
		.thenReturn(new Lancamento("Martelo", LocalDate.parse("2021-12-13"), new Categoria("Equipamentos", BigDecimal.valueOf(300.00)), TipoLancamento.DESPESA, BigDecimal.valueOf(300.00)));
		
		MvcResult result = mockMvc.perform(get(url+"/1"))
		.andExpect(status()
				.isOk()).andReturn();
		 LancamentoDto lancamentoView = mapper.readValue(result.getResponse().getContentAsString(), LancamentoDto.class);
		 Assertions.assertEquals("eletronicos",lancamentoView.getDescricao());
	}

}
