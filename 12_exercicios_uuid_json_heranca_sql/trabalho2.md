# Trabalho 2 – Evolução do Spoti Pobre (5 pontos)

## Objetivo

Evoluir o sistema **Spoti Pobre**, desenvolvido a partir do banco de dados PostgreSQL disponibilizado na disciplina, implementando uma aplicação web Java para gerenciamento de músicas, álbuns, artistas, usuários e playlists.

O trabalho deverá explorar recursos avançados do PostgreSQL e sua integração com Java, incluindo armazenamento de dados semiestruturados, arquivos binários, herança de tabelas e controle de permissões.

A aplicação deverá utilizar obrigatoriamente:

- PostgreSQL;
- JDBC;
- Javalin;
- Mustache;
- JSONB;
- BYTEA;
- Herança de tabelas PostgreSQL;
- DCL (`GRANT` e `REVOKE`);
- Padrão DAO (*Data Access Object*).

### Restrições técnicas

Não será permitido utilizar:

- Hibernate;
- JPA;
- ORM em geral;
- Comandos SQL diretamente nas rotas do Javalin.

**Todo o SQL deverá estar nas classes DAO.** As rotas deverão delegar as operações de persistência aos DAOs, mantendo a separação entre a camada web, a lógica da aplicação e o acesso ao banco de dados.

O trabalho é **individual**. Cada estudante deverá desenvolver e entregar seu próprio projeto funcional, utilizando o banco de dados `spoti_pobre` fornecido na disciplina.

---

## Requisito 1 – JSONB no PostgreSQL (1,0 ponto)

Implementar funcionalidades que utilizem colunas do tipo `JSONB` para armazenar informações complementares relacionadas às entidades do Spoti Pobre.

O estudante deverá escolher pelo menos uma entidade do sistema, como `playlist`, `musica`, `album` ou `usuario`, e adicionar uma coluna `JSONB`.

### Exemplos de aplicação

Uma playlist poderá armazenar informações complementares:

```
{
  "descricao": "Seleção de clássicos brasileiros",
  "tags": ["mpb", "brasil", "classicos"],
  "clima": "nostalgico",
  "destaque": true
}
```

Uma música poderá armazenar informações adicionais:

```
{
  "idioma": "pt-BR",
  "ao_vivo": false,
  "instrumentos": ["violao", "baixo", "bateria"]
}
```

Os exemplos são ilustrativos. O estudante poderá definir outras informações coerentes com o sistema.

### Implementação obrigatória

A aplicação deverá permitir:

1. Inserir e atualizar informações JSON utilizando JDBC.
2. Consultar e apresentar os dados armazenados em JSONB.
3. Implementar pelo menos uma busca que utilize operadores ou funções JSON do PostgreSQL.
4. Disponibilizar uma funcionalidade acessível pela aplicação web que demonstre essa busca.

Exemplo de consulta:

```
SELECT id, nome
FROM playlist
WHERE dados_extras->>'destaque' = 'true';
```

Também poderão ser utilizados operadores como `@>`, `?`, `->` e `->>`, conforme a estrutura escolhida.

Não será suficiente adicionar uma coluna JSONB sem utilizá-la efetivamente na aplicação.

---

## Requisito 2 – BYTEA: armazenamento e gerenciamento de arquivos (1,0 ponto)

Implementar uma funcionalidade de armazenamento e gerenciamento de arquivos binários no PostgreSQL utilizando o tipo `BYTEA`.

O banco fornecido já possui a coluna `album.capa BYTEA`, que poderá ser aproveitada. Alternativamente, poderá ser implementada outra funcionalidade coerente com o sistema.

### Funcionalidade sugerida

Implementar o gerenciamento das capas dos álbuns cadastrados no Spoti Pobre.

A aplicação deverá permitir:

- **Inserir:** enviar uma imagem e armazenar seu conteúdo binário no PostgreSQL.
- **Visualizar:** exibir a capa do álbum na página web, recuperando o conteúdo do banco de dados.
- **Atualizar:** substituir a capa existente por uma nova imagem.
- **Excluir:** remover a capa armazenada, preservando o cadastro do álbum.

### Requisitos técnicos

1. Utilizar JDBC para gravar e recuperar o conteúdo binário.
2. Utilizar recursos apropriados para manipulação de dados binários, como `PreparedStatement`, `setBytes`, `setBinaryStream` ou `getBytes`.
3. Disponibilizar rotas web para as operações de gerenciamento.
4. Exibir a imagem recuperada do banco de dados no navegador.

A aplicação deverá efetivamente armazenar e recuperar os bytes do arquivo. Não será suficiente armazenar apenas o nome, o caminho ou a URL da imagem.

---

## Requisito 3 – Herança de tabelas PostgreSQL e Java (1,0 ponto)

Utilizar herança de tabelas PostgreSQL para representar uma especialização de alguma entidade do Spoti Pobre.

**Não poderá ser utilizada a especialização `usuario_premium`, já desenvolvida em aula.** Deverá ser proposta e implementada outra especialização, adequada ao domínio musical.

### Possibilidades de especialização

O estudante poderá escolher, por exemplo:

- **Álbuns especiais:** uma tabela `album_coletanea` que herde de `album`, com atributos específicos para coletâneas, como tema e ano de referência.
- **Músicas ao vivo:** uma tabela `musica_ao_vivo` que herde de `musica`, com atributos como local e data da apresentação.
- **Playlists temáticas:** uma tabela `playlist_temática` que herde de `playlist`, com atributos específicos da temática escolhida.
- **Artistas independentes:** uma tabela especializada de `artista`, com atributos adicionais relacionados à carreira independente.

As possibilidades acima são sugestões. Poderá ser proposta outra especialização, desde que faça sentido no contexto do sistema.

### Implementação obrigatória

1. Criar uma tabela especializada utilizando `INHERITS` no PostgreSQL.
2. Definir os atributos específicos da especialização.
3. Implementar classes Java que representem a classe geral e a classe especializada, utilizando herança entre classes.
4. Implementar operações de persistência para cadastrar e consultar registros da especialização.
5. Disponibilizar na aplicação web uma funcionalidade que demonstre o uso da especialização.

A implementação deverá considerar as particularidades da herança do PostgreSQL, incluindo o comportamento das consultas às tabelas pai e filhas e as restrições de integridade necessárias.

Não será suficiente utilizar apenas herança entre classes Java, apenas tabelas relacionadas por chaves estrangeiras ou reproduzir a especialização `usuario_premium` feita em aula.

---

## Requisito 4 – Aplicação Web com Javalin, Mustache, JDBC e DAO (1,0 ponto)

Desenvolver uma aplicação web funcional para o Spoti Pobre utilizando Javalin e templates Mustache.

A aplicação deverá ser executada na porta:

```
7000
```

### Funcionalidades obrigatórias

Implementar as operações de CRUD completo para pelo menos uma entidade do sistema:

- **Listagem:** apresentar os registros cadastrados.
- **Cadastro:** permitir inserir novos registros.
- **Consulta individual:** apresentar os detalhes de um registro selecionado.
- **Edição:** permitir alterar os dados de um registro existente.
- **Remoção:** permitir excluir um registro.

O estudante poderá escolher a entidade principal do CRUD, como `playlist`, `musica`, `album`, `artista` ou outra entidade coerente com o sistema.

As funcionalidades implementadas nos demais requisitos também deverão estar integradas à aplicação web.

### Requisitos técnicos

- **Javalin:** definição das rotas e tratamento das requisições HTTP.
- **Mustache:** renderização das páginas HTML.
- **JDBC:** conexão e comunicação com o PostgreSQL.
- **DAO Pattern:** centralização das operações SQL em classes específicas de acesso a dados.

As rotas não poderão conter comandos SQL. Deverão receber os dados das requisições, chamar os serviços ou DAOs apropriados e retornar páginas ou respostas HTTP.

O código deverá demonstrar separação adequada de responsabilidades entre as rotas, as classes de domínio, os DAOs e os templates.

---

## Requisito 5 – Controle de acesso PostgreSQL (DCL) (1,0 ponto)

Implementar controle de acesso ao banco de dados utilizando comandos DCL do PostgreSQL, especialmente `GRANT` e `REVOKE`.

Deverão ser criados usuários ou papéis com diferentes níveis de permissão sobre as tabelas do Spoti Pobre.

### Perfis obrigatórios

**1. Administrador da aplicação**

Deverá possuir as permissões necessárias para executar as operações previstas pela aplicação, incluindo consultas, inserções, atualizações e remoções nas tabelas pertinentes.

**2. Usuário somente leitura**

Deverá conseguir consultar os dados autorizados, mas não poderá inserir, atualizar ou excluir registros.

### Exemplo

```
CREATE ROLE spoti_consulta LOGIN PASSWORD 'senha_consulta';

GRANT CONNECT ON DATABASE spoti_pobre
TO spoti_consulta;

GRANT USAGE ON SCHEMA public
TO spoti_consulta;

GRANT SELECT ON
    usuario,
    playlist,
    musica,
    album,
    artista
TO spoti_consulta;

REVOKE INSERT, UPDATE, DELETE ON
    usuario,
    playlist,
    musica,
    album,
    artista
FROM spoti_consulta;
```

O exemplo é ilustrativo. O estudante deverá adaptar os comandos às tabelas utilizadas, incluindo as tabelas criadas para atender aos demais requisitos.

### Implementação obrigatória

1. Criar os papéis ou usuários com diferentes níveis de acesso.
2. Utilizar `GRANT` para conceder permissões.
3. Utilizar `REVOKE` para retirar ou restringir permissões.
4. Incluir os comandos no script SQL de entrega.
5. Demonstrar, por meio de testes, que o usuário somente leitura consegue consultar os dados, mas não consegue executar operações de escrita nas tabelas protegidas.

As permissões deverão ser efetivamente testadas no PostgreSQL. Não será suficiente apresentar comandos SQL sem demonstrar seus efeitos.

---

## Entrega

O estudante deverá entregar individualmente, pelo SIGAA:

1. **Projeto Java completo e funcional**, contendo o código-fonte, as dependências e os arquivos necessários à execução.
2. **Script SQL completo**, incluindo as alterações de estrutura, as tabelas e colunas adicionadas, os dados necessários para testes e os comandos de controle de acesso.
3. **Dados para testes**, suficientes para demonstrar o funcionamento das funcionalidades implementadas.
4. **Instruções de execução**, informando como configurar a conexão JDBC, preparar o banco de dados e iniciar a aplicação.

O script deverá permitir preparar o banco e reproduzir as alterações necessárias para executar o sistema.

Antes de compactar o projeto, remover diretórios e arquivos desnecessários à execução, especialmente ambientes virtuais (`.venv`, `venv` e equivalentes), caches, arquivos temporários e artefatos de compilação que possam ser regenerados.

Não incluir senhas reais ou credenciais pessoais na entrega. Utilizar credenciais de teste e documentar os parâmetros necessários para configuração.

---

## Critérios de avaliação

| Requisito                                                                                          | Pontuação      |
| -------------------------------------------------------------------------------------------------- | -------------- |
| JSONB: armazenamento, consulta e busca por operadores JSON                                         | 1,0            |
| BYTEA: CRUD de arquivos binários integrado à aplicação                                             | 1,0            |
| Herança PostgreSQL e herança entre classes Java, com especialização diferente de `usuario_premium` | 1,0            |
| Aplicação web: Javalin, Mustache, JDBC, DAO e CRUD completo                                        | 1,0            |
| DCL: `GRANT`, `REVOKE` e testes de permissões                                                      | 1,0            |
| **Total**                                                                                          | **5,0 pontos** |

## Observações finais

- O trabalho é individual e deverá utilizar o banco Spoti Pobre fornecido na disciplina.
- As funcionalidades deverão estar integradas ao domínio musical e ser demonstradas na aplicação ou por testes reproduzíveis.
- Código que apenas declare estruturas, sem implementar as funcionalidades solicitadas, não será considerado suficiente.
- A organização do código, a clareza da implementação e a consistência dos dados serão consideradas na avaliação de cada requisito.
