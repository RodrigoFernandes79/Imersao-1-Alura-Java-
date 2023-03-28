package br.com.alura.linguagensapi.domains;

public record AtualizacaoVotoLinguagem(String id, String titulo, Integer votos) {

	public AtualizacaoVotoLinguagem(Linguagem linguagem) {
		this(linguagem.getId(), linguagem.getTitle(), linguagem.getVotes());
	}

}
