public class CriadorDeFrases {
	// metodo para criar frases de acordo com classificaçao de estrelas
	public Frases geradorDeFrasesDeAcordoComClassificacao(String classificacao) {

		String star = "\u2B50";
		if (classificacao != null) {
			int ranking = Integer.parseInt(classificacao);

			return geradorDeFrasesDeAcordoComClassificacao(ranking);
		}

		String frase = "MANEIRO!";
		String estrelas = star.repeat(5);
		return new Frases(estrelas, frase);
	}

	public Frases geradorDeFrasesDeAcordoComClassificacao(int classificacao) {
    String star = "\u2B50";
    if (classificacao <= 10) {
        String frase = "TOOPZERA";
        String estrelas = star.repeat(5);
        return new Frases(estrelas, frase);
    } else if (classificacao > 10 && classificacao <= 30) {
        String frase = "DAHORA";
        String estrelas = star.repeat(4);
        return new Frases(estrelas, frase);
    } else {
        String frase = "MARROMENOS";
        String estrelas = star.repeat(3);
        return new Frases(estrelas, frase);
    }
}
}
