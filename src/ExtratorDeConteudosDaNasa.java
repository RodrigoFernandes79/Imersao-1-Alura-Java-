import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudosDaNasa implements ExtratorDeConteudos {

	public List<Conteudo> extrairConteudos(String json) {
		// extrair só os dados que interessam (título, poster e classificação)
		var jsonParser = new JsonParser();
		List<Map<String, String>> listaDeAtributos = jsonParser.parse(json);

		List<Conteudo> listaDeConteudos = new ArrayList<>();
		// exibir e manipular os dados na aplicação
		listaDeAtributos.forEach(atributosMap -> {
			String tituloCompleto = atributosMap.get("title");
			String poster = atributosMap.get("hdurl");

			Conteudo conteudo = new Conteudo(tituloCompleto, poster, null);
			listaDeConteudos.add(conteudo);

		});
		return listaDeConteudos;
	}
}
