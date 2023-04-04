package br.com.alura.linguagensapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import br.com.alura.linguagensapi.domains.Linguagem;

@SpringBootTest
@AutoConfigureDataMongo
@ActiveProfiles("test")
public class LinguagemRepositoryTests {

	@MockBean
	private LinguagemRepository repository;

	@Test
	@DisplayName("Deve retornar todas as linguagens ordenadas pelo ranking em ordem crescente")
	void testFindAllByOrderByRankingAsc() {
		List<Linguagem> linguagens = List.of(
				new Linguagem("Java", "https://exemplo.com/java.png", null, 1),
				new Linguagem("Python", "https://exemplo.com/python.png", null, 2));
		when(repository.findAllByOrderByRankingAsc()).thenReturn(linguagens);

		List<Linguagem> result = repository.findAllByOrderByRankingAsc();
		assertEquals(linguagens.size(), result.size());
		assertEquals(linguagens.get(0).getTitle(), result.get(0).getTitle());
		assertEquals(linguagens.get(1).getTitle(), result.get(1).getTitle());
	}
}
