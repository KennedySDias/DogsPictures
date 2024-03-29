# DogsPictures

O aplicativo “DogsPictures” é uma galeria de fotos de cachorros, onde o usuário seleciona uma raça e em seguida é exibida uma lista de images de cachorros com aquela raça selecionada, com opção para visisualizar em modo de tela cheia.

O aplicativo consume uma API específica (https://dog.ceo/dog-api/documentation/) e faz cache da mesma utilizando o Google Room. Foi desenvolvida seguindo os princípios do Clean Architecture, separando em camadas de `Data`, `Domain` e `Presentation`.

- `Data` utiliza as bibliotecas Retrofit, para consumir a API externa, e Room (Google Architecture Components) para a persistência de dados e cache.

- `Domain` foi implementado os `Use Cases` com as regras que acessam a camada `Data` em busca dos dados, sejam externos ou locais. Realizando as tarefas de consulta de dados utilizando Coroutines, na thread IO e retornar os dados para thread MAIN com mapeamento dos tipos de erros (Auth, No Connetion, Message, others)

- `Presentation` é responsável por toda a parte visual do aplicativo, como Activities, Fragments, ViewModels e etc. Foi implementado seguindo os padrões da arquitetura MVVM e programação reativa (LiveData).
Também utilizando algumas bibliotecas como:
  - Data Binding: Para o binding direto dos modelos de dados com as interfaces visuais.
  - Glide: Carregamento de imagens.
  - Koin: Injeção de dependências.
  - Material: Componentes de UI do Android.
  
O aplicativo foi desenvolvido utilizando a linguagem Kotlin e toda as comunições entre as camadas são feitas através de Coroutines.

## Como utilizar
- Visualize e selecione uma raça de cachorro (breed)
- Será exibido uma lista de imagens de cachorros da raça selecionada.
- Toque sobre uma imagem para visualizar ela em modo de tela cheia.

## Como Executar
- Clone ou baixe o repositório em seu computador. 
- Abra o projeto utilizando o Android Studio.
- Aguarde o Androd Studio baixar e instalar todas as dependências.
- Execute o projeto clicando no botão `Run` na parte superior direita.
- Selecione um Emulador ou dispositivo conectado para executar o aplicativo.

## Como executar os testes
- Abra o terminal
- Vá até a parta do projeto
- Execute o comando `./gradlew test` para executar os Testes de Unidade
- Execute o comando `./gradlew connectedAndroidTest` para executar os Testes de Integração/UI

## Imagens
<img src="images/01.png" width="250"> <img src="images/02.png" width="250"><img src="images/03.png" width="250">
<br>
<img src="images/04.png" width="600">
<br>
<img src="images/05.png" width="600">
