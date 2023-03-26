import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudosDeLinguagens implements ExtratorDeConteudos {

	public List<Conteudo> extrairConteudos(String json) {
		// extrair só os dados que interessam (título, poster e classificação)
		var jsonParser = new JsonParser();
		List<Map<String, String>> listaDeAtributos = jsonParser.parse(json);

		List<Conteudo> listaDeConteudos = new ArrayList<>();
		// exibir e manipular os dados na aplicação
		listaDeAtributos.forEach(atributosMap -> {
			String tituloCompleto = atributosMap.get("titulo");
			String poster = atributosMap.get("imagem");
			String classificacao = atributosMap.get("classificacao");

			Conteudo conteudo = new Conteudo(tituloCompleto, poster, classificacao);
			listaDeConteudos.add(conteudo);

		});
		return listaDeConteudos;
	}
}
