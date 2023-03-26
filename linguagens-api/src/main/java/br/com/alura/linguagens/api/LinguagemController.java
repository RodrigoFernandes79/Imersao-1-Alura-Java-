package br.com.alura.linguagens.api;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.linguagens.api.domains.AtualizacaoVotoLinguagem;
import br.com.alura.linguagens.api.domains.DadosEntradaLinguagem;
import br.com.alura.linguagens.api.domains.DadosSaidaLinguagem;
import br.com.alura.linguagens.api.domains.Linguagem;
import br.com.alura.linguagens.api.domains.VotosDto;

@RestController
@RequestMapping("/linguagens")
public class LinguagemController {

	@Autowired
	private LinguagemRepository linguagemRepository;

	@Autowired
	private LinguagemService linguagemService;

	@PostMapping
	@Transactional
	public ResponseEntity<DadosSaidaLinguagem> criarLinguagem(@RequestBody DadosEntradaLinguagem dadosEntradaLinguagem,
			UriComponentsBuilder uriComponentsBuilder) {

		Linguagem linguagemObj = linguagemService.criarLinguagem(dadosEntradaLinguagem);
		URI URI = uriComponentsBuilder.path("linguagens/{id}")
				.buildAndExpand(linguagemObj.getId())
				.toUri();
		// Retornar o status 201 quando um recurso (linguagem, no nosso caso) for
		// cadastrado através do POST;
		return ResponseEntity.created(URI).body(new DadosSaidaLinguagem(linguagemObj));
	}

	@GetMapping
	public ResponseEntity<List<DadosSaidaLinguagem>> getLinguagens() {
		// Devolver a listagem ordenada pelo ranking;
		List<Linguagem> linguagens = linguagemRepository.findAllByOrderByRankingAsc();

		var saida = linguagens.stream().map(DadosSaidaLinguagem::new)
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(saida);

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deletarLinguagemPorId(@PathVariable String id) {

		linguagemService.deletarLinguagemPorId(id);
		return ResponseEntity.noContent().build();

	}

	@PatchMapping("/{id}/votos")
	@Transactional
	public ResponseEntity<AtualizacaoVotoLinguagem> atualizarVotos(@PathVariable String id,
			@RequestBody VotosDto votosDto) {

		var linguagem = linguagemService.atualizarVotos(id, votosDto);
		return ResponseEntity.ok().body(linguagem);
	}

}
