package com.leonardo.financeiro.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

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
import com.leonardo.financeiro.model.dto.CategoriaDto;
import com.leonardo.financeiro.service.CategoriaService;

@AutoConfigureMockMvc
@WebMvcTest(controllers = {CategoriaController.class})
class CategoriaControllerTest {

	private final String url= "/categoria";
	private final ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CategoriaService categoriaService;
	
	@Test
	void testSalvar() throws Exception {

		doReturn(new Categoria("Equipamentos", BigDecimal.valueOf(300.00))).when(categoriaService).salvar(Mockito.any());
		String body = "{\"nome\":\"Equipamentos\"}";
		mockMvc
		.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body)).andExpect(status().isCreated());
	}

	@Test
	void deveriaRetornarSucesso200OKQuandoBuscarCategoriaPorId() throws Exception {
		
		when(categoriaService.buscarPorId(1))
		.thenReturn(new Categoria("Equipamentos", BigDecimal.valueOf(300.00)));
		
		MvcResult result = mockMvc.perform(get(url+"/1"))
		.andExpect(status()
				.isOk()).andReturn();
		 CategoriaDto categoriaView = mapper.readValue(result.getResponse().getContentAsString(), CategoriaDto.class);
		 Assertions.assertEquals("eletronicos",categoriaView.getNome());
	}
	
}
