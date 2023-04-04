package br.com.alura.linguagensapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.linguagensapi.domains.DadosEntradaLinguagem;
import br.com.alura.linguagensapi.domains.DadosSaidaLinguagem;
import br.com.alura.linguagensapi.domains.Linguagem;
import br.com.alura.linguagensapi.exceptions.ObjetoNaoEncontrado;

@SpringBootTest
@AutoConfigureDataMongo
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
public class LinguagemControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LinguagemService service;

	@Autowired
	private JacksonTester<DadosEntradaLinguagem> dadosEntradaJTester;

	@Autowired
	private JacksonTester<DadosSaidaLinguagem> dadosSaidaJTester;

	@MockBean
	private LinguagemRepository repository;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("Deveria devolver código 400 quando URL da imagem já existir no banco de dados")
	public void criarLinguagem_Cenario1() throws Exception {

		var entradaLinguagem = new DadosEntradaLinguagem(
				"Java", "https://exemplo.com/java.png", null, 1);

		String json = dadosEntradaJTester.write(entradaLinguagem).getJson();
		when(service.criarLinguagem(entradaLinguagem)).thenThrow(ObjetoNaoEncontrado.class);

		MockHttpServletResponse response = mockMvc.perform(post("/linguagens")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andReturn()
				.getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	@DisplayName("Deveria devolver código 201 quando informações forem inseridas")
	public void criarLinguagem_Cenario2() throws Exception {

		DadosEntradaLinguagem entradaLinguagem = new DadosEntradaLinguagem(
				"Java", "https://exemplo.com/java.png", 12, 1);

		String json = dadosEntradaJTester.write(entradaLinguagem).getJson();

		Linguagem linuagem = new Linguagem(entradaLinguagem);
		when(service.criarLinguagem(any())).thenReturn(linuagem);
		MockHttpServletResponse response = mockMvc.perform(post("/linguagens")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andReturn()
				.getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

		var jsonEsperado = dadosSaidaJTester.write(new DadosSaidaLinguagem(linuagem)).getJson();

		assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

	}

	@Test
	@DisplayName("Deveria devolver lista ordenada pelo ranking")
	public void getLinguagens() throws Exception {
		// Cria algumas linguagens e salva no banco de dados
		Linguagem linguagem1 = new Linguagem(
				"Java", "https://exemplo.com/java.png", 12, 5);
		Linguagem linguagem2 = new Linguagem(
				"Python", "https://exemplo.com/python.png", 11, 6);
		repository.saveAll(Arrays.asList(linguagem1, linguagem2));

		// Chama o endpoint e armazena a resposta em um objeto MvcResult
		MockHttpServletResponse response = mockMvc.perform(get("/linguagens")
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn()
				.getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

		// Extrai o corpo da resposta e converte em uma lista de DadosSaidaLinguagem
		List<DadosSaidaLinguagem> linguagensSaida = objectMapper.readValue(
				response.getContentAsString(),
				new TypeReference<List<DadosSaidaLinguagem>>() {
				});

		// Verifica se a lista está ordenada pelo ranking
		assertThat(linguagensSaida)
				.extracting(DadosSaidaLinguagem::classificacao)
				.isSorted();
	}

}
