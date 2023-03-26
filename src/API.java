public enum API {
	API_IMDB_TOP250_MOVIES(
			"Imdb: Top 250 Movies",
			"https://imdb-api.com/en/API/Top250Movies/" + App.API_KEY_IMDB,
			new ExtratorDeConteudosDoImdb()

	),
	API_IMDB_MOST_POPULAR_MOVIES(
			"Imdb: Most Popular Movies",
			"https://imdb-api.com/en/API/MostPopularMovies/" + App.API_KEY_IMDB,
			new ExtratorDeConteudosDoImdb()

	),
	API_NASA(
			"NASA: APOD Astronomy Picture of the Day",
			"https://api.nasa.gov/planetary/apod?api_key=" + App.API_KEI_NASA +
					"&&start_date=2023-03-16&&end_date=2023-03-18",
			new ExtratorDeConteudosDaNasa()

	),
	API_LINGUAGENS(
			"LINGUAGENS: API Criada no Projeto",
			"http://localhost:8080/linguagens",
			new ExtratorDeConteudosDeLinguagens()

	);

	private final String descricao;
	private final String urlApi;
	private final ExtratorDeConteudos extratorDeConteudos;

	API(String descricao, String urlApi, ExtratorDeConteudos extratorDeConteudos) {
		this.descricao = descricao;
		this.urlApi = urlApi;
		this.extratorDeConteudos = extratorDeConteudos;

	}

	public String getDescricao() {
		return descricao;
	}

	public String getUrlApi() {
		return urlApi;
	}

	public ExtratorDeConteudos getExtratorDeConteudos() {
		return extratorDeConteudos;
	}
}
