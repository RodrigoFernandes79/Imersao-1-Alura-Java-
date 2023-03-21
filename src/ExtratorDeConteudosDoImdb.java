import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudosDoImdb implements ExtratorDeConteudos {

	public List<Conteudo> extrairConteudos(String json) {
		var jsonParser = new JsonParser();
		List<Map<String, String>> listaDeAtributos = jsonParser.parse(json);
		List<Conteudo> listaDeConteudos = new ArrayList<>();
		// exibir e manipular os dados na aplicação
		listaDeAtributos.forEach(atributosMap -> {
			String titulo = atributosMap.get("fullTitle");
			String poster = atributosMap.get("image");
			boolean contemCaracteres = poster.contains("@.");
			if (contemCaracteres) {
				String UrlTratada = tratadorDeUrlImagem(poster);

				String classificacao = atributosMap.get("rank");

				Conteudo conteudo = new Conteudo(titulo, UrlTratada, classificacao);
				listaDeConteudos.add(conteudo);
			}
		});
		return listaDeConteudos;

	}

	private static String tratadorDeUrlImagem(String poster) {
		int x = poster.indexOf("@.");

		int y = poster.indexOf(".jpg");

		String removerStringThumbnail = poster.substring(x + 1, y);

		String posterReplaced = poster.replace(removerStringThumbnail, "");
		return posterReplaced;

	}

}
