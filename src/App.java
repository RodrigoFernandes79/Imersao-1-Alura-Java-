import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
	// escondendoO a chave de acesso da api IMDB em uma variavel de ambiente do
	// windows
	public static final String API_KEI_NASA = System.getenv("API_KEI_NASA");
	public static final String API_KEY_IMDB = System.getenv("API_KEY_IMDB");

	public static void main(String[] args) throws Exception {

		String urlAPINASA = API.API_NASA.getUrlApi();
		// String urlApiIMDBPopular = API.API_IMDB_MOST_POPULAR_MOVIES.getUrlApi();
		// String urlApiTop250 = API.API_IMDB_TOP250_MOVIES.getUrlApi();
		// chamando o metodo retornaDadosApí() para retornar os dados da api:
		ClienteHttp clienteHttp = new ClienteHttp();
		String body = clienteHttp.retornaDadosApi(urlAPINASA);

		ExtratorDeConteudos extratorDeConteudos = API.API_NASA.getExtratorDeConteudos();
		GeradoraDeFigurinhas geradoraDeFigurinhas = new GeradoraDeFigurinhas();
		List<Conteudo> extrairConteudos = extratorDeConteudos.extrairConteudos(body);
		for (Conteudo conteudo : extrairConteudos) {

			var nomeArquivo = conteudo.titulo() + ".png";
			System.out.println("\u001b[37m \u001b[44m " + conteudo.titulo() + " \u001b[m");
			InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
			System.out.println(conteudo.urlImagem());
			var classificacao = conteudo.classificacao();

			// Chamando a classe que possui o metodo de criar frases de acordo com a
			// classificação do filme
			CriadorDeFrases criadorDeFrases = new CriadorDeFrases();
			Frases gerarFrases = criadorDeFrases.geradorDeFrasesDeAcordoComClassificacao(classificacao);
			String frase = gerarFrases.frase();
			String stars = gerarFrases.stars();
			System.out.println("\u001b[37m \u001b[44m nota: " + stars + " \u001b[m");
			System.out.println();

			// Criando figurinhas com o metodo da classe GeradoraDeFigurinhas:
			// Tratar as imagens retornadas pela API do IMDB para pegar uma imagem maior ao
			// invés dos thumbnails.

			// criando figurinhas

			geradoraDeFigurinhas.criarFigurinha(inputStream, nomeArquivo, frase, stars);
		}

		// // exibir e manipular os dados na aplicação -AULA 1

		// for (Map<String, String> conteudosMap : listaDeConteudos) {
		// String tituloCompleto = conteudosMap.get("fullTitle");
		// String emoji = "\uD83E\uDD2F";
		// System.out.print(emoji);
		// System.out.println("\u001b[37m \u001b[44m " + tituloCompleto + " \u001b[m");

		// String poster = conteudosMap.get("hdurl");
		// String classificacao = conteudosMap.get("imDbRating");
		// System.out.println("\u001b[37m \u001b[44m nota: " + classificacao + "
		// \u001b[m");
		// int stars = Integer.parseInt(classificacao.substring(0, 1));

		// for (int i = 1; i <= stars; i++) {
		// System.out.print("\u2B50");
		// }
		// System.out.println();
		// }
	}
}
