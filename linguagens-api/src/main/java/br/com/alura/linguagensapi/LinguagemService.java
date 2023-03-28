package br.com.alura.linguagensapi;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.linguagensapi.domains.AtualizacaoVotoLinguagem;
import br.com.alura.linguagensapi.domains.DadosEntradaLinguagem;
import br.com.alura.linguagensapi.domains.Linguagem;
import br.com.alura.linguagensapi.domains.VotosDto;
import br.com.alura.linguagensapi.exceptions.EntidadeNaoEncontrada;
import br.com.alura.linguagensapi.exceptions.ObjetoNaoEncontrado;

@Service
public class LinguagemService {

	@Autowired
	private LinguagemRepository linguagemRepository;

	public Linguagem criarLinguagem(DadosEntradaLinguagem dadosEntradaLinguagem) {
		Optional<Linguagem> findByUrlImage = linguagemRepository.findByUrlImage(dadosEntradaLinguagem.imagem());
		if (findByUrlImage.isPresent()) {
			throw new ObjetoNaoEncontrado("Url já existe no banco de dados!");
		}
		Linguagem linguagemObj = new Linguagem(dadosEntradaLinguagem);

		if (dadosEntradaLinguagem.votos() == null) {
			linguagemObj.setVotes(0);
		}
		linguagemRepository.save(linguagemObj);
		atualizarRanking();
		return linguagemObj;
	}

	public void deletarLinguagemPorId(String id) {
		var idObj = linguagemRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontrada("Id não existe no Banco de Dados"));

		linguagemRepository.delete(idObj);
		atualizarRanking();// chama o método para atualizar o ranking após a atualização de votos

	}

	public AtualizacaoVotoLinguagem atualizarVotos(String id, VotosDto votosDto) {
		Linguagem linguagemObj = linguagemRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontrada("Linguagem não encontrada"));
		/*
		 * Desafio supremo: Aplicar modificações parciais no recurso através do método
		 * PATCH,
		 * por exemplo, modificando o número de votos ou de pessoas
		 * que utilizam cada linguagem de programação.
		 */
		int novoVoto = votosDto.getVoto();
		int votosAntigos = linguagemObj.getVotes();
		linguagemObj.setVotes(votosAntigos + novoVoto);

		linguagemRepository.save(linguagemObj);

		atualizarRanking();// chama o método para atualizar o ranking após a atualização de votos

		var linguagemAtualizada = new AtualizacaoVotoLinguagem(linguagemObj);
		return linguagemAtualizada;
	}

	public void atualizarRanking() { // chama o método para atualizar o ranking após a atualização de votos
		List<Linguagem> linguagens = linguagemRepository.findAll();

		// ordena a lista pelo número de votos (em ordem decrescente)
		linguagens.sort(Comparator.comparingInt(Linguagem::getVotes).reversed());

		// atualiza o ranking de cada linguagem de acordo com a posição na lista
		for (int i = 0; i < linguagens.size(); i++) {

			Linguagem linguagem = linguagens.get(i);
			linguagem.setRanking(i + 1);
			linguagemRepository.save(linguagem);
		}
	}
}
