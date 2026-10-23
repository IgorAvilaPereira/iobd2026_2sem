# Lista 1 SQL 

## Parte 1 – Exercícios 1 a 20

**Tema:** SELECT, WHERE, ORDER BY, INNER JOIN e SCHEMAS

Base de dados: **spoti_pobre**. 

---

# Exercício 1

Liste o nome e o e-mail de todos os usuários.





---

# Exercício 2

Liste todos os usuários em ordem alfabética pelo nome.




---

# Exercício 3

Liste apenas os usuários nascidos após 01/01/1998.




---

# Exercício 4

Mostre apenas o nome das playlists públicas.





---

# Exercício 5

Liste todas as músicas com duração superior a cinco minutos.






---

# Exercício 6

Liste os álbuns lançados antes de 1980.






---

# Exercício 7

Liste os artistas em ordem decrescente.





---

# Exercício 8

Liste as músicas lançadas depois de 1990.






---

# Exercício 9

Mostre todos os gêneros cadastrados.






---

# Exercício 10

Liste todas as playlists privadas.







---

# Exercício 11

Liste o nome do usuário e o nome da playlist da qual ele é dono.





---

# Exercício 12

Liste todas as músicas juntamente com o álbum ao qual pertencem.






---

# Exercício 13

Liste o título do álbum e o gênero correspondente.







---

# Exercício 14

Liste cada artista e seus respectivos álbuns.






---

# Exercício 15

Liste cada usuário e a música que ele reproduziu.




---

# Exercício 16

Liste as playlists e todas as músicas presentes nelas.






---

# Exercício 17

Liste as músicas juntamente com o artista responsável.






---

# Exercício 18

Liste todas as músicas, seus álbuns, artistas e gêneros.







---

# Exercício 19

Liste todas as reproduções realizadas, mostrando:

* usuário
* música
* álbum
* artista
* data da reprodução







---

# Exercício 20

## SCHEMAS

Crie um schema chamado **backup** e mova a tabela **playlist** para esse schema.





---

# Apostila de SQL PostgreSQL – Exercícios

# Parte 2 – Exercícios 21 a 40

**Tema:** LEFT JOIN, RIGHT JOIN, GROUP BY e Funções de Agregação

Base de dados: **spoti_pobre**. 

---

# Exercício 21

Liste todos os usuários e suas playlists. Usuários sem playlist também devem aparecer.





---

# Exercício 22

Liste todas as playlists e seus respectivos donos. Caso alguma playlist não possua dono, ela também deve aparecer.





---

# Exercício 23

Liste todas as músicas e informe em qual playlist elas aparecem.





---

# Exercício 24

Liste todos os artistas e seus álbuns.





---

# Exercício 25

Liste todos os gêneros e seus respectivos álbuns.





---

# Exercício 26

Liste todas as músicas e informe se já foram reproduzidas por algum usuário.






---

# Exercício 27

Liste todos os usuários e as músicas reproduzidas.





---

# Exercício 28

Liste todos os artistas e as músicas gravadas por eles.





---

# Exercício 29

Liste todas as playlists e a quantidade de músicas que possuem.




---

# Exercício 30

Liste todos os usuários e quantas playlists participam.




---

# Exercício 31

Utilize **RIGHT JOIN** para listar todos os usuários associados às playlists.






---

# Exercício 32

Utilize **RIGHT JOIN** para listar todas as músicas e suas reproduções.







---

# Exercício 33

Conte quantos usuários existem.





---

# Exercício 34

Conte quantas playlists existem.




---

# Exercício 35

Conte quantas músicas existem em cada álbum.





---

# Exercício 36

Conte quantos álbuns cada artista possui.







---

# Exercício 37

Conte quantas playlists existem por usuário.







---

# Exercício 38

Calcule a duração média das músicas.





---

# Exercício 39

Mostre a menor, a maior e a média duração das músicas.





---

# Exercício 40

Monte um relatório contendo:

* Nome do artista
* Quantidade de álbuns
* Quantidade de músicas
* Quantidade de gêneros diferentes

Ordene do artista com maior quantidade de músicas para o menor.






---

# Apostila de SQL PostgreSQL – Exercícios

# Parte 3 – Exercícios 41 a 60

**Tema:** HAVING, Manipulação de Datas e Manipulação de Strings

Base de dados: **spoti_pobre**. 

---

# Exercício 41

Liste os artistas que possuem mais de um álbum cadastrado.




---

# Exercício 42

Liste os usuários que participam de mais de uma playlist.




---

# Exercício 43

Liste os artistas que possuem pelo menos duas músicas cadastradas.





---

# Exercício 44

Liste as playlists que possuem mais de duas músicas.





---

# Exercício 45

Liste os gêneros que possuem mais de um álbum.



---

# Exercício 46

Mostre o ano de nascimento de cada usuário.






---

# Exercício 47

Mostre o mês de criação de cada playlist.




---

# Exercício 48

Mostre o dia da semana em que cada reprodução ocorreu.





---

# Exercício 49

Liste todas as músicas lançadas na década de 1970.





---

# Exercício 50

Mostre quantos anos cada álbum possui.






---

# Exercício 51

Mostre somente o ano de lançamento dos álbuns.





---

# Exercício 52

Liste os usuários em letras maiúsculas.







---

# Exercício 53

Liste os artistas em letras minúsculas.





---

# Exercício 54

Liste os gêneros com apenas a primeira letra de cada palavra maiúscula.






---

# Exercício 55

Crie uma coluna contendo:


Nome - Email






---

# Exercício 56

Mostre apenas os cinco primeiros caracteres do nome das músicas.






---

# Exercício 57

Substitua a palavra "Brasil" por "BR" nos nomes das playlists.







---

# Exercício 58

Mostre o tamanho do nome de cada artista.





---

# Exercício 59

Remova espaços em branco antes e depois do texto `' PostgreSQL '`.





---

# Exercício 60

Construa um relatório contendo:

* Nome do artista em maiúsculas;
* Ano do álbum;
* Quantidade de músicas;
* Somente artistas com mais de uma música;
* Ordenado pela quantidade de músicas.







---

# Apostila de SQL PostgreSQL – Exercícios

# Parte 4 – Exercícios 61 a 80

**Tema:** Subconsultas (Subselects), EXISTS, IN, ANY, ALL, CTEs e Views

Base de dados: **spoti_pobre**. 

---

# Exercício 61

Liste os usuários que possuem pelo menos uma reprodução cadastrada utilizando `IN`.







---

# Exercício 62

Liste as músicas que pertencem a algum álbum utilizando `IN`.




---

# Exercício 63

Liste os artistas que possuem pelo menos um álbum lançado antes da média de lançamento de todos os álbuns.





---

# Exercício 64

Liste os usuários que nunca reproduziram nenhuma música utilizando `NOT IN`.





---

# Exercício 65

Liste as playlists que possuem pelo menos uma música.




---

# Exercício 66

Liste os artistas que possuem álbuns do gênero MPB.





---

# Exercício 67

Utilize `EXISTS` para listar os usuários que possuem reproduções.




---

# Exercício 68

Utilize `NOT EXISTS` para listar os usuários que nunca ouviram músicas.





---

# Exercício 69

Liste as playlists que possuem mais músicas que a média de músicas por playlist.





---

# Exercício 70

Liste os álbuns cuja data de lançamento é igual à menor data cadastrada.





---

# Exercício 71

Utilize `ANY` para listar músicas com duração maior que pelo menos uma música do álbum 3.






---

# Exercício 72

Utilize `ALL` para listar músicas maiores que todas as músicas do álbum 3.






---

# Exercício 73

Crie uma CTE contendo todos os artistas e seus álbuns.





---

# Exercício 74

Crie uma CTE contendo a quantidade de músicas por playlist.






---

# Exercício 75

Crie uma View chamada **vw_artistas** contendo:

* artista
* álbum
* gênero




---

# Exercício 76

Consulte a View criada anteriormente.




---

# Exercício 77

Crie uma View contendo todas as reproduções.

Campos:

* usuário
* música
* data




---

# Exercício 78

Crie uma View contendo:

* Playlist
* Quantidade de músicas







---

# Exercício 79

Utilize uma View para listar apenas playlists com mais de duas músicas.





---

# Exercício 80

Crie uma CTE que calcule:

* artista
* quantidade de álbuns
* quantidade de músicas

Depois liste apenas artistas com pelo menos um álbum e duas músicas.

;


---
# Apostila de SQL PostgreSQL – Exercícios

# Parte 5 – Exercícios 81 a 100

**Tema:** `STRING_AGG`, Relatórios Avançados, Views, CTEs e Desafios Integradores

Base de dados: **spoti_pobre**. 

---

# Exercício 81

Liste cada álbum juntamente com todas as suas músicas em uma única linha utilizando `STRING_AGG`.





---

# Exercício 82

Liste cada playlist juntamente com todas as músicas separadas por vírgula.





---

# Exercício 83

Liste cada artista juntamente com todos os seus álbuns utilizando `STRING_AGG`.





---

# Exercício 84

Liste cada gênero e todos os álbuns pertencentes a ele.





---

# Exercício 85

Liste cada usuário e todas as playlists das quais participa.




---

# Exercício 86

Construa um relatório contendo:

* Artista
* Álbum
* Quantidade de músicas
* Duração média das músicas





---

# Exercício 87

Liste os cinco artistas com maior quantidade de músicas.





---

# Exercício 88

Liste as cinco playlists com maior quantidade de músicas.





---

# Exercício 89

Liste os usuários que ouviram músicas do gênero MPB.







---

# Exercício 90

Liste todos os artistas que possuem músicas em playlists.






---

# Exercício 91

Crie uma CTE contendo todas as reproduções realizadas em julho de 2026.





---

# Exercício 92

Crie uma View contendo:

* usuário
* quantidade de reproduções







---

# Exercício 93

Utilize a View anterior para listar apenas usuários com mais de uma reprodução.



---

# Exercício 94

Crie um relatório contendo:

* usuário
* playlist
* quantidade de músicas da playlist





---

# Exercício 95

Liste os artistas que possuem músicas com duração superior à média de duração de todas as músicas.






---

# Exercício 96

Liste os usuários que ouviram músicas lançadas antes de 1980.



---

# Exercício 97

Monte um relatório contendo:

* artista
* gênero
* quantidade de álbuns
* quantidade de músicas





---

# Exercício 98

Monte uma consulta que mostre:

* Playlist
* Dono
* Colaboradores

Os colaboradores devem aparecer em uma única coluna utilizando `STRING_AGG`.







---

# Exercício 99

Crie uma View contendo todas as informações das músicas:

* música
* artista
* álbum
* gênero
* duração




CREATE VIEW vw_catalogo AS


---

# Exercício 100 (Desafio Final)

Utilizando **CTE**, **Views**, **GROUP BY**, **HAVING**, **LEFT JOIN**, **STRING_AGG**, **Subconsultas**, **EXTRACT** e **funções de agregação**, construa um relatório contendo:

* Nome do artista
* Gênero
* Quantidade de álbuns
* Quantidade de músicas
* Duração média das músicas
* Lista dos álbuns (`STRING_AGG`)
* Ano do primeiro álbum
* Ano do último álbum

Liste apenas artistas que possuem pelo menos um álbum e ordene pela quantidade de músicas.

---

# Apostila de SQL PostgreSQL – Exercícios

# Parte 6 – Exercícios 101 a 120

**Tema:** UUID, JSON, Herança de Tabelas, Índices e DCL

**Base de dados:** `spoti_pobre`

**Material de apoio:**

- [UUID](https://github.com/IgorAvilaPereira/iobd2026_2sem/blob/main/01_introducao/dicas/dicas.md#uuid)
- [JSON](https://github.com/IgorAvilaPereira/iobd2026_2sem/blob/main/01_introducao/dicas/dicas.md#json)
- [Herança (Especialização/Generalização) de Tabelas no PostgreSQL](https://github.com/IgorAvilaPereira/iobd2026_2sem/blob/main/01_introducao/dicas/dicas.md#heran%C3%A7a-especializa%C3%A7%C3%A3ogeneraliza%C3%A7%C3%A3o-de-tabelas-no-postgresql)
- [Índices](https://github.com/IgorAvilaPereira/iobd2026_2sem/blob/main/11_heranca_json_postgresql/indices.md)

---

## Parte 6.1 – UUID

### Exercício 101

A tabela `usuario` possui a coluna `hash_id`, do tipo `UUID`, configurada com geração automática por meio da função `uuid_generate_v4()`.

Escreva uma consulta SQL que liste:

- O identificador numérico do usuário;
- O nome;
- O e-mail;
- O UUID (`hash_id`).

Ordene o resultado pelo nome do usuário.

### Exercício 102

Insira um novo usuário na tabela `usuario`, informando nome, e-mail, senha e data de nascimento, mas sem fornecer um valor para `hash_id`.

Em seguida, consulte o registro inserido e verifique se o PostgreSQL gerou automaticamente o UUID.

### Exercício 103

Insira um usuário na tabela `usuario` informando explicitamente um UUID para a coluna `hash_id`.

Depois, consulte o registro para verificar se o valor informado foi armazenado corretamente.

### Exercício 104

Escreva uma consulta que localize um usuário a partir de seu UUID (`hash_id`), em vez de utilizar o identificador numérico `id`.

Explique por que o UUID pode ser utilizado como identificador em aplicações que precisam referenciar registros sem expor diretamente a chave numérica.

### Exercício 105

A tabela `usuario` possui um índice associado à coluna `hash_id`.

Escreva os comandos SQL necessários para:

a) Consultar os índices existentes na tabela `usuario`.

b) Criar um índice B-tree para a coluna `hash_id`, utilizando um nome diferente do índice existente.

c) Remover o índice criado no item anterior.

d) Consultar o plano de execução de uma pesquisa de usuário pelo UUID, utilizando `EXPLAIN`.

---

## Parte 6.2 – JSON

### Exercício 106

A tabela `backup.log`, localizada no schema `backup`, possui a coluna `detalhes`, do tipo `JSON`.

Insira três registros de log com diferentes conteúdos na coluna `detalhes`. Utilize documentos JSON para representar informações como:

- Tipo de operação;
- Tabela afetada;
- Usuário responsável;
- Quantidade de registros afetados.

### Exercício 107

Consulte todos os registros da tabela `backup.log`, exibindo o identificador, o comentário e o conteúdo da coluna `detalhes`.

Ordene os registros pelo identificador.

### Exercício 108

Considerando os documentos JSON inseridos no exercício anterior, escreva uma consulta que extraia o valor da propriedade `operacao` de cada registro.

Exiba o identificador do log, o comentário e o valor extraído.

### Exercício 109

Escreva uma consulta que liste apenas os registros da tabela `backup.log` cuja propriedade `tabela` contenha o valor `usuario`.

Utilize os operadores de extração de JSON do PostgreSQL.

### Exercício 110

Acrescente um novo registro à tabela `backup.log` contendo, no campo `detalhes`, um documento JSON com informações sobre a criação de uma playlist.

O documento deverá conter, no mínimo:

- Nome da playlist;
- Indicação de que ela é pública;
- Quantidade inicial de músicas;
- Identificação do usuário responsável.

Depois, consulte o registro inserido e extraia individualmente essas propriedades.

### Exercício 111

A tabela `backup.log` armazena informações complementares no campo `detalhes`.

Escreva uma consulta que liste os registros que possuem a propriedade `operacao` e outra que liste os registros que não possuem essa propriedade.

Explique a diferença entre uma propriedade inexistente e uma propriedade cujo valor é `null` em um documento JSON.

---

## Parte 6.3 – Herança de Tabelas no PostgreSQL

### Exercício 112

A tabela `usuario_premium` foi criada utilizando `INHERITS (usuario)` e possui as colunas adicionais `plano`, `valor_mensal` e `validade`.

Escreva uma consulta que liste todos os usuários cadastrados, incluindo os registros da tabela `usuario_premium`, exibindo:

- Identificador;
- Nome;
- E-mail;
- Plano;
- Valor mensal.

Para os usuários que não possuem os atributos específicos de um plano premium, apresente valores adequados para representar a ausência dessas informações.

### Exercício 113

Escreva duas consultas SQL:

a) Uma que liste os registros da tabela `usuario`, incluindo os registros das tabelas que herdam dela.

b) Outra que liste exclusivamente os registros armazenados diretamente na tabela `usuario`, sem incluir os registros das tabelas filhas.

Explique a diferença entre as duas consultas no PostgreSQL.

### Exercício 114

Insira um usuário comum na tabela `usuario` e um usuário premium na tabela `usuario_premium`.

Depois, execute consultas para verificar em quais resultados cada registro aparece.

Considere que `usuario_premium` herda as colunas de `usuario`, mas possui também atributos próprios.

### Exercício 115

A tabela `usuario_premium` possui uma chave primária própria para a coluna `id` e uma restrição `UNIQUE` para `email`.

Realize as seguintes operações:

a) Consulte as restrições definidas na tabela `usuario_premium`.

b) Explique por que a definição de uma chave primária na tabela filha é necessária para estabelecer essa restrição nessa tabela.

c) Analise se as restrições de unicidade definidas separadamente em `usuario` e `usuario_premium` garantem que não existam e-mails repetidos entre as duas tabelas.

d) Explique uma limitação da herança de tabelas do PostgreSQL em relação à integridade referencial e à unicidade entre tabelas relacionadas por herança.

### Exercício 116

Considere a tabela `usuario_premium` e a tabela de relacionamento `usuario_playlist`.

Escreva uma consulta que liste as playlists associadas a usuários premium, exibindo:

- Nome do usuário premium;
- E-mail;
- Plano;
- Nome da playlist;
- Indicador de proprietário (`dono`);
- Indicador de colaborador (`colaborador`).

Utilize o relacionamento estabelecido pela coluna `usuario_premium_id`.

---

## Parte 6.4 – Índices

### Exercício 117

Crie um índice B-tree para a coluna `nome` da tabela `musica`.

Em seguida:

a) Consulte o plano de execução de uma pesquisa por nome de música utilizando `EXPLAIN`.

b) Execute uma consulta que pesquise uma música pelo nome.

c) Explique como o índice pode contribuir para o desempenho dessa consulta.

d) Remova o índice criado.

### Exercício 118

A tabela `reproducao` armazena o histórico de músicas reproduzidas pelos usuários.

Crie um índice composto para as colunas `usuario_id` e `quando`.

Em seguida, escreva consultas que pesquisem:

a) Todas as reproduções de um usuário específico.

b) Todas as reproduções de um usuário específico em determinado intervalo de datas.

c) Todas as reproduções realizadas em determinado intervalo de datas, independentemente do usuário.

Utilize `EXPLAIN` para analisar os planos de execução e discuta em quais situações o índice composto pode ser útil.

### Exercício 119

A tabela `playlist_musica` possui uma chave primária composta pelas colunas `playlist_id` e `musica_id`.

Realize as seguintes operações:

a) Consulte os índices existentes na tabela.

b) Explique qual índice é criado automaticamente para dar suporte à chave primária.

c) Analise se uma consulta que pesquisa músicas por `musica_id` pode se beneficiar desse índice.

d) Caso considere necessário, crie um índice adicional para a coluna `musica_id`.

e) Utilize `EXPLAIN` para analisar o plano de execução das consultas antes e depois da criação do índice adicional.

---

## Parte 6.5 – DCL (Data Control Language)

### Exercício 120

Considere a base de dados `spoti_pobre`, que possui informações de usuários, músicas, álbuns, artistas, playlists e reproduções.

Utilizando os comandos DCL do PostgreSQL, implemente uma política de controle de acesso para os seguintes perfis:

- **Administrador:** pode consultar e modificar os dados das tabelas do sistema.
- **Analista:** pode consultar usuários, músicas, álbuns, artistas, playlists e reproduções, mas não pode modificar os dados.
- **Responsável pelos logs:** pode consultar e inserir registros na tabela `backup.log`, sem poder modificar ou excluir registros existentes.

Implemente os seguintes requisitos:

a) Crie as roles necessárias para representar os três perfis.

b) Conceda ao administrador as permissões necessárias para consultar, inserir, atualizar e excluir registros das tabelas do schema `public`.

c) Conceda ao analista apenas permissões de consulta nas tabelas indicadas.

d) Conceda ao responsável pelos logs permissões para consultar e inserir registros em `backup.log`, sem conceder permissões de atualização ou exclusão.

e) Conceda as permissões necessárias para que os usuários dessas roles consigam acessar os schemas e utilizar as tabelas correspondentes.

f) Crie usuários de banco de dados para testar os perfis e associe cada usuário à role correspondente.

g) Demonstre, por meio de comandos SQL, operações permitidas e negadas para cada perfil.

h) Utilize `REVOKE` para retirar do analista uma permissão previamente concedida e demonstre o efeito da revogação.

i) Explique a diferença entre criar uma role, conceder privilégios a uma role e associar um usuário a uma role.

**Observação:** diferencie os privilégios sobre o schema dos privilégios sobre as tabelas. Considere também que permissões concedidas sobre tabelas existentes não são automaticamente aplicadas a todas as tabelas criadas futuramente. Analise como `ALTER DEFAULT PRIVILEGES` pode ser utilizado para definir permissões padrão para novos objetos.




---



