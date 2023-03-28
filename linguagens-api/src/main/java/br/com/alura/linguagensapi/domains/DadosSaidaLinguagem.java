package br.com.alura.linguagensapi.domains;

public record DadosSaidaLinguagem(String titulo, String imagem, Integer classificacao,
 Integer votos) {


	public DadosSaidaLinguagem(Linguagem linguagens) {
		this(linguagens.getTitle(), linguagens.getUrlImage(), linguagens.getRanking(),linguagens.getVotes());
	}

}
