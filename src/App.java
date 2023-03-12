import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
				//Fazer uma conexão Http e buscar os filmes mais populares
				/*código extraído da documentação
				https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpRequest.html */
				String api_key = System.getenv("API_KEY_IMDB");

				String url ="https://imdb-api.com/en/API/MostPopularMovies/"+api_key;
				var endereco = URI.create(url);
				var client = HttpClient.newHttpClient();
				var request = HttpRequest.newBuilder(endereco).GET().build();

				HttpResponse<String> response = client.send(request,  BodyHandlers.ofString());
				String body = response.body();


				// extrair só os dados que interessam (título, poster e classificação)
				var jsonParser = new JsonParser();
				List<Map<String,String>> listaDeFilmes = jsonParser.parse(body);

				// exibir e manipular os dados na aplicação
				for (Map<String,String> filmesMap : listaDeFilmes) {
					String títuloCompleto = filmesMap.get("fullTitle");
					String emoji = "\uD83E\uDD2F";
					System.out.print(emoji);
					System.out.println("\u001b[37m \u001b[44m " +títuloCompleto+ " \u001b[m");
					String poster = filmesMap.get("image");
					System.out.println(poster);
					String classificacao = filmesMap.get("imDbRating");
					System.out.println("\u001b[37m \u001b[44m nota: "  +classificacao+" \u001b[m" );
					int stars = Integer.parseInt(classificacao.substring(0,1));
					for(int i = 1; i <= stars; i++) {
						System.out.print("\u2B50");
					}
		System.out.println();
	}
}
}



