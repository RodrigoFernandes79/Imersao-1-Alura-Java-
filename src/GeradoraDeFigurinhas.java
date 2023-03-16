import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

	public void criarFigurinha(InputStream inputStream, String nomeArquivo, String frase, String classificacao)
			throws IOException, FontFormatException {
		// leitura da imagem
		/*
		 * InputStream openStream = new URL(
		 * "https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@.jpg")
		 * .openStream();
		 */
		BufferedImage imagemOriginal = ImageIO.read(inputStream);

		// criar nova imagem em memória com transparência e com novo tamanho
		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura + 300;
		BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

		// copiar a imagem origimal para a imagem em memória
		Graphics2D modelarimagem = (Graphics2D) novaImagem.getGraphics();
		modelarimagem.drawImage(imagemOriginal, 0, 0, null);

		// configurar fonte da frase na imagem
		// Colocar outra fonte como a Comic Sans ou a Impact, a fonte usada em memes
		InputStream impactFont = new FileInputStream(new File("fontes/impact.ttf"));
		Font createFontImpact = Font.createFont(Font.TRUETYPE_FONT, impactFont);
		var fonte = new Font(createFontImpact.getFontName(), Font.TYPE1_FONT, 120);

		modelarimagem.setFont(fonte);
		modelarimagem.setColor(Color.YELLOW);
		// Centralizar o texto na figurinha

		if (frase == null) {
			frase = "DEMAIS!";
		}
		int tamanhoFrase = modelarimagem.getFontMetrics().stringWidth(frase);
		int posicaoLargura = (novaImagem.getWidth() - tamanhoFrase) / 2;
		int posicaoAltura = novaAltura - 150;
		// escrever uma frase na nova imagem
		modelarimagem.drawString(frase, posicaoLargura, posicaoAltura);

		// inserir estrelas de classificação embaixo da frase
		InputStream emojiFont = new FileInputStream(new File("fontes/NotoColorEmoji.ttf"));
		Font createEmojiFont = Font.createFont(Font.TRUETYPE_FONT, emojiFont);
		var fonteEmoji = new Font(createEmojiFont.getFontName(), Font.TYPE1_FONT, 120);

		modelarimagem.setFont(fonteEmoji);
		modelarimagem.setColor(Color.YELLOW);
		// Centralizar as estrelas de classificação na figurinha embaixo da frase

		int tamanhoEstrelas = modelarimagem.getFontMetrics().stringWidth(classificacao);
		int xLargura = (novaImagem.getWidth() - tamanhoEstrelas) / 2;
		int yAltura = novaAltura - 50;
		// escrever classificaçao rating (estrelas) na nova imagem
		modelarimagem.drawString(classificacao, xLargura, yAltura);

		// adicionar o arquivo em um diretorio
		File path = new File(Path.of("").toAbsolutePath().toString() + "/saida.figurinha");
		if (!path.exists()) {
			path.mkdirs();
			// escrever a nova imagem em um arquivo dentro do diretorio criado
			ImageIO.write(novaImagem, "png", new File(path + "/" + nomeArquivo));
		} else {
			// escrever a nova imagem em um arquivo
			ImageIO.write(novaImagem, "png", new File(path + "/" + nomeArquivo));
		}

	}

}
