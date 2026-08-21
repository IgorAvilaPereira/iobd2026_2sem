## Trabalho 1: Sistema Web SpotiPobre (Java + Javalin + Mustache + Postgres)

A aplicação consiste em um sistema web onde o Javalin processa as requisições, interage com o PostgreSQL através do JDBC nativo e exibe os resultados na tela por meio de templates do Mustache.

------------------------------
## ⚙️ Funcionalidades e Distribuição de Pontos

## 1. Criação e Vínculo de Playlist (GET e POST /playlists/nova) — [Valor: 1,5 Pontos]

* Interface Web (Mustache): Uma página contendo um formulário HTML para cadastrar uma nova playlist (campos: nome e uma caixa de seleção para publica ou privada).
* Processamento e Banco (JDBC/Postgres):
* Ao enviar o formulário, o sistema insere o novo registro na tabela playlist.
   * Como o sistema não possui login, o formulário deve conter um campo para selecionar qual usuario está criando a playlist. O sistema realiza um segundo INSERT na tabela de relacionamento usuario_playlist, definindo o usuário selecionado como dono (dono = true).

## 2. Tela Principal: Listagem de Álbuns e Músicas (GET /dashboard) — [Valor: 1,5 Pontos]

* Interface Web (Mustache): Um painel geral que exibe os dados do catálogo musical do sistema em formato de tabela ou listagem estruturada.
* Processamento e Banco (JDBC/Postgres):
* O sistema executa uma consulta no banco de dados realizando um JOIN entre as tabelas album, musica e album_musica.
   * A consulta recupera os títulos dos álbuns e suas respectivas músicas e envia essa lista diretamente para o template Mustache ser renderizado no navegador.

## 3. Funcionalidade: Registrar Reprodução de Música (POST /reproduzir) — [Valor: 2,0 Pontos]

* Interface Web (Mustache): Na listagem de músicas do painel principal, cada faixa possui um formulário simples com um botão "Ouvir" e uma seleção com a lista de usuários do sistema.
* Processamento e Banco (JDBC/Postgres):
* Ao clicar no botão, o sistema captura o ID da música acionada e o ID do usuário selecionado.
   * O sistema executa um comando INSERT por meio do JDBC na tabela reproducao, gravando o histórico de qual usuário ouviu aquela música no momento atual. Após a inserção, a página é atualizada para exibir o catálogo novamente.


