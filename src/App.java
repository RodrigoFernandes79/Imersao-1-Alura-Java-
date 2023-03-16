import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
	public static void main(String[] args) throws Exception {

		// escondendo a chave de acesso da api IMDB em uma variavel de ambiente do
		// windows
		String api_key = System.getenv("API_KEY_IMDB");
		// Fazer uma conexão Http e buscar os filmes mais populares
		/*
		 * código extraído da documentação
		 * https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/
		 * http/HttpRequest.html
		 */
		String url = "https://imdb-api.com/en/API/Top250Movies/" + api_key;
		var endereco = URI.create(url);
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(endereco).GET().build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();

		// extrair só os dados que interessam (título, poster e classificação)
		var jsonParser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = jsonParser.parse(body);

		// exibir e manipular os dados na aplicação
		for (Map<String, String> filmesMap : listaDeFilmes) {
			String tituloCompleto = filmesMap.get("fullTitle");
			String emoji = "\uD83E\uDD2F";
			System.out.print(emoji);
			System.out.println("\u001b[37m \u001b[44m " + tituloCompleto + " \u001b[m");

			String poster = filmesMap.get("image");
			String classificacao = filmesMap.get("imDbRating");
			System.out.println("\u001b[37m \u001b[44m nota: " + classificacao + " \u001b[m");
			int stars = Integer.parseInt(classificacao.substring(0, 1));

			for (int i = 1; i <= stars; i++) {
				System.out.print("\u2B50");
			}
			System.out.println();
			// Criando figurinhas com o metodo da classe GeradoraDeFigurinhas:
			// Tratar as imagens retornadas pela API do IMDB para pegar uma imagem maior ao
			// invés dos thumbnails.
			String removerStringThumbnail = "._V1_UX128_CR0,12,128,176_AL_";
			InputStream innputStream = new URL(poster.replace(removerStringThumbnail, "")).openStream();
			String nomeArquivo = tituloCompleto + ".png";

			GeradoraDeFigurinhas geradoraDeFigurinhas = new GeradoraDeFigurinhas();
			// criando figurinhas
			geradoraDeFigurinhas.criarFigurinha(innputStream, nomeArquivo, starClass(stars), rating(stars));

		}
	}

	// metodo para criar frases de acordo com classificaçao de estrelas
	public static String starClass(int stars) {

		if (stars >= 9) {
			return "TOOPZERA";
		}
		if (stars >= 7 && stars <= 8) {
			return "DAHORA!";
		}
		return "MARROMENOS";
	}

	// metodo para criar estrelas de acordo com a classificaçao
	public static String rating(int stars) {
		String star = "\n\u2B50\n";
		return star.repeat(stars);
	}
}
