package br.com.alura.linguagens.api.domains;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PrincipaisLinguagens") // mapeamento entre a entidade e a collection do Bd mongoDb
public class Linguagem {

	private String id;
	private String title;
	private String urlImage;
	private Integer ranking;
	private Integer votes;

	public Linguagem() {
	}

	public Linguagem(String title, String urlImage, Integer ranking, Integer votes) {
		this.title = title;
		this.urlImage = urlImage;
		this.ranking = ranking;
		this.votes = votes;
	}

	public Linguagem(DadosEntradaLinguagem dadosEntradaLinguagem) {
		this.title = dadosEntradaLinguagem.titulo();
		this.urlImage = dadosEntradaLinguagem.imagem();
		this.ranking = dadosEntradaLinguagem.classificacao();
		this.votes = dadosEntradaLinguagem.votos();
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {

		this.ranking = ranking;
	}

	public Integer getVotes() {
		return votes;

	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}
}
