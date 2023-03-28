## Alura Stickers - Imersão Java - Alura 🤖
#ImersãoJava

Projeto em Java realizado através da imersão Java Alura 1. A aplicação consome algumas API's como a do IMDB, da NASA e a languagesAPI(criada durante o evento) com as informações retornadas por elas é feita a criação de stickers para o Whatsapp.

## Desafios da Aula 01 🏷️

![terminalIMDB](https://user-images.githubusercontent.com/83513696/226100492-33941975-fd19-48ad-a0c9-fc6d2369024e.png)


- `Consumir o endpoint de filmes mais populares da API do IMDB. Procure também, na documentação da API do IMDB, o endpoint que retorna as melhores séries e o que retorna as séries mais populares.`

- `Usar sua criatividade para deixar a saída dos dados mais bonitinha: usar emojis com código UTF-8, mostrar a nota do filme como estrelinhas, decorar o terminal com cores, negrito e itálico usando códigos ANSI, e mais!`

- `Colocar a chave da API do IMDB em algum lugar fora do código como um arquivo de configuração (p. ex, um arquivo .properties) ou uma variável de ambiente`

- `Mudar o JsonParser para usar uma biblioteca de parsing de JSON como Jackson ou GSON`

- `Desafio supremo: criar alguma maneira para você dar uma avaliação ao filme, puxando de algum arquivo de configuração OU pedindo a avaliação para o usuário digitar no terminal.`

##  Desafios da Aula 02 🏷️

Nesta segunda aula vamos criar um gerador de figurinhas explorando outras bibliotecas nativas do Java, para que possamos enviar por Whatsapp os nossos filmes preferidos!

![godfather](https://user-images.githubusercontent.com/83513696/225489837-6b8756b3-b1ed-4e21-afb2-f584e55e0eae.png)
![backtothefuture](https://user-images.githubusercontent.com/83513696/225490283-36c7719e-7249-4fc0-8609-88e2d2d668b8.png)

- `Ler a documentação da classe abstrata InputStream.`
- `Centralizar o texto na figurinha.`
- `Fazer um pacote no Whatsapp e/ou Telegram com as suas próprias figurinhas!`
- `Criar diretório de saída das imagens, se ainda não existir.`
- `Colocar outra fonte como a Comic Sans ou a Impact, a fonte usada em memes.`
- `Colocar uma imagem de você que está fazendo esse curso sorrindo, fazendo joinha!`
- `Colocar contorno (outline) no texto da imagem.`
- `Tratar as imagens retornadas pela API do IMDB para pegar uma imagem maior ao invés dos thumbnails. Opções: pegar a URL da imagem e remover o trecho mostrado durante a aula ou consumir o endpoint de posters da API do IMDB (mais trabalhoso), tratando o JSON retornado.`
- `Fazer com que o texto da figurinha seja personalizado de acordo com as classificações do IMDB.`
- `Desafio supremo: usar alguma biblioteca de manipulação de imagens como OpenCV pra extrair imagem principal e contorná-la.`

##  Desafios da Aula 03 🏷️

Chegou o momento de pegarmos os filmes do IMDb e gerar figurinhas com os pôsteres, aproveitando para melhorar nosso código com as refatorações necessárias para torná-lo mais flexível e fácil de entender.

![babylon](https://user-images.githubusercontent.com/83513696/228370908-8b8db079-49cb-47c3-803a-a2ff4a265770.png)
![nasa](https://user-images.githubusercontent.com/83513696/228371024-054a8f7c-7811-4266-ab50-7d8cedf8c2e3.png)


- `Transformar a classe que representa os conteúdos em um Record, disponível a partir do Java 16`
- `Criar as suas próprias exceções e usá-las na classe que implementa o cliente HTTP`
- `Usar recursos do Java 8 e posterior, como Streams e Lambdas, para mapear uma lista em uma outra`
- `Criar uma Enum que une, como configurações, a URL da API e o extrator utilizado`
- `Desafio supremo: consumir outras APIs que contém imagens, como a da Marvel, que é bem diferente.`

##  Desafios da Aula 04 🏷️

Agora vamos construir uma API REST para expor nosso próprio conteúdo, utilizando ferramentas profissionais como o Spring Framework e um banco de dados NoSQL.

![Java](https://user-images.githubusercontent.com/83513696/228371371-13896fd3-43ea-4983-90e9-3e07d1b8c617.png)

- `Finalizar o CRUD (Create, Read, Update e Delete) para que se possa atualizar e excluir uma linguagem cadastrada;`
- `Devolver a listagem ordenada pelo ranking;`
- `Criar na sua API um modelo de entidade com nomes diferentes de title e image e criar seu próprio extrator de informações personalizado OU, manter com o nome title e image e traduzir para que seja retornado como título e imagem através do uso de DTO (Data Transfer Object);`
- `Retornar o status 201 quando um recurso (linguagem, no nosso caso) for cadastrado através do POST;`
- `Desafio supremo: Aplicar modificações parciais no recurso através do método PATCH, por exemplo, modificando o número de votos ou de pessoas que utilizam cada linguagem de programação.`

##  Desafios da Aula 05 🏷️
![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)

