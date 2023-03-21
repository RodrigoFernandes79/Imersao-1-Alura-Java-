import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import exceptions.HttpClientException;

public class ClienteHttp {

	public String retornaDadosApi(String url) {
		// Fazer uma conexão Http e buscar os filmes mais populares
		/*
		 * código extraído da documentação
		 * https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/
		 * http/HttpRequest.html
		 */
		try {
			var endereco = URI.create(url);
			var client = HttpClient.newHttpClient();
			var request = HttpRequest.newBuilder(endereco).GET().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			String body = response.body();
			return body;

		} catch (IOException | InterruptedException e) {
			throw new HttpClientException(e.getMessage());
		}

	}

}
