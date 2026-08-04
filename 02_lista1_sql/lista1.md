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



