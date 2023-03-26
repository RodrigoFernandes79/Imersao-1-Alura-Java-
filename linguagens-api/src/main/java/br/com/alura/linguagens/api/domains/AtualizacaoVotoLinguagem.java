package br.com.alura.linguagens.api.domains;

public record AtualizacaoVotoLinguagem(String id, String titulo, Integer votos) {

	public AtualizacaoVotoLinguagem(Linguagem linguagem) {
		this(linguagem.getId(),linguagem.getTitle(),linguagem.getVotes());
	}


}
