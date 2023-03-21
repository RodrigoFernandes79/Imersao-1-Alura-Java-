public class CriadorDeFrases {
	// metodo para criar frases de acordo com classificaçao de estrelas
	public Frases geradorDeFrasesDeAcordoComClassificacao(String classificacao) {

		String star = "\u2B50";
		if (classificacao != null) {
			int ranking = Integer.parseInt(classificacao);

			if (ranking <= 10) {
				String frase = "TOOPZERA";
				String estrelas = star.repeat(5);

				return new Frases(estrelas, frase);

			}
			if (ranking > 10 && ranking <= 30) {
				String frase = "DAHORA";
				String estrelas = star.repeat(4);
				return new Frases(estrelas, frase);

			}
			String frase = "MARROMENOS";
			String estrelas = star.repeat(3);
			return new Frases(estrelas, frase);

		}
		String frase = "MANEIRO!";
		String estrelas = star.repeat(5);
		return new Frases(estrelas, frase);
	}

}
