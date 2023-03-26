package br.com.alura.linguagens.api;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.alura.linguagens.api.domains.Linguagem;

public interface LinguagemRepository extends MongoRepository<Linguagem, String> {

	public List<Linguagem> findAllByOrderByRankingAsc();

	public Optional<Linguagem> findByUrlImage(String urlImage);

}
