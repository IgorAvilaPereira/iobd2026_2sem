# Apostila de SQL PostgreSQL – Exercícios

## Parte 1 – Exercícios 1 a 20

**Tema:** SELECT, WHERE, ORDER BY, INNER JOIN e SCHEMAS

Base de dados: **spoti_pobre**. 

---

# Exercício 1

**Liste o nome e o e-mail de todos os usuários.**

## Gabarito

```sql
SELECT nome, email
FROM usuario;
```

---

# Exercício 2

Liste todos os usuários em ordem alfabética pelo nome.

## Gabarito

```sql
SELECT *
FROM usuario
ORDER BY nome;
```

---

# Exercício 3

Liste apenas os usuários nascidos após 01/01/1998.

## Gabarito

```sql
SELECT *
FROM usuario
WHERE data_nascimento > '1998-01-01';
```

---

# Exercício 4

Mostre apenas o nome das playlists públicas.

## Gabarito

```sql
SELECT nome
FROM playlist
WHERE publica = TRUE;
```

---

# Exercício 5

Liste todas as músicas com duração superior a cinco minutos.

## Gabarito

```sql
SELECT *
FROM musica
WHERE duracao > '00:05:00';
```

---

# Exercício 6

Liste os álbuns lançados antes de 1980.

## Gabarito

```sql
SELECT titulo,
       data_lancamento
FROM album
WHERE data_lancamento < '1980-01-01';
```

---

# Exercício 7

Liste os artistas em ordem decrescente.

## Gabarito

```sql
SELECT *
FROM artista
ORDER BY nome DESC;
```

---

# Exercício 8

Liste as músicas lançadas depois de 1990.

## Gabarito

```sql
SELECT nome,
       data_hora_lancamento
FROM musica
WHERE data_hora_lancamento >= '1990-01-01';
```

---

# Exercício 9

Mostre todos os gêneros cadastrados.

## Gabarito

```sql
SELECT *
FROM genero;
```

---

# Exercício 10

Liste todas as playlists privadas.

## Gabarito

```sql
SELECT *
FROM playlist
WHERE publica = FALSE;
```

---

# Exercício 11

Liste o nome do usuário e o nome da playlist da qual ele é dono.

## Gabarito

```sql
SELECT u.nome,
       p.nome
FROM usuario u
INNER JOIN usuario_playlist up
ON up.usuario_id = u.id
INNER JOIN playlist p
ON p.id = up.playlist_id
WHERE up.dono = TRUE;
```

---

# Exercício 12

Liste todas as músicas juntamente com o álbum ao qual pertencem.

## Gabarito

```sql
SELECT
m.nome,
a.titulo
FROM musica m
INNER JOIN album_musica am
ON am.musica_id = m.id
INNER JOIN album a
ON a.id = am.album_id;
```

---

# Exercício 13

Liste o título do álbum e o gênero correspondente.

## Gabarito

```sql
SELECT
a.titulo,
g.nome
FROM album a
INNER JOIN genero g
ON g.id = a.genero_id;
```

---

# Exercício 14

Liste cada artista e seus respectivos álbuns.

## Gabarito

```sql
SELECT
ar.nome,
al.titulo
FROM artista ar
INNER JOIN album_artista aa
ON aa.artista_id = ar.id
INNER JOIN album al
ON al.id = aa.album_id;
```

---

# Exercício 15

Liste cada usuário e a música que ele reproduziu.

## Gabarito

```sql
SELECT
u.nome,
m.nome,
r.quando
FROM usuario u
INNER JOIN reproducao r
ON r.usuario_id = u.id
INNER JOIN musica m
ON m.id = r.musica_id;
```

---

# Exercício 16

Liste as playlists e todas as músicas presentes nelas.

## Gabarito

```sql
SELECT
p.nome,
m.nome
FROM playlist p
INNER JOIN playlist_musica pm
ON pm.playlist_id = p.id
INNER JOIN musica m
ON m.id = pm.musica_id;
```

---

# Exercício 17

Liste as músicas juntamente com o artista responsável.

## Gabarito

```sql
SELECT
m.nome,
ar.nome
FROM musica m
INNER JOIN album_musica am
ON am.musica_id = m.id
INNER JOIN album_artista aa
ON aa.album_id = am.album_id
INNER JOIN artista ar
ON ar.id = aa.artista_id;
```

---

# Exercício 18

Liste todas as músicas, seus álbuns, artistas e gêneros.

## Gabarito

```sql
SELECT
m.nome AS musica,
al.titulo,
ar.nome AS artista,
g.nome AS genero
FROM musica m
INNER JOIN album_musica am
ON am.musica_id = m.id
INNER JOIN album al
ON al.id = am.album_id
INNER JOIN album_artista aa
ON aa.album_id = al.id
INNER JOIN artista ar
ON ar.id = aa.artista_id
INNER JOIN genero g
ON g.id = al.genero_id;
```

---

# Exercício 19

Liste todas as reproduções realizadas, mostrando:

* usuário
* música
* álbum
* artista
* data da reprodução

## Gabarito

```sql
SELECT
u.nome,
m.nome,
al.titulo,
ar.nome,
r.quando
FROM reproducao r
INNER JOIN usuario u
ON u.id = r.usuario_id
INNER JOIN musica m
ON m.id = r.musica_id
INNER JOIN album_musica am
ON am.musica_id = m.id
INNER JOIN album al
ON al.id = am.album_id
INNER JOIN album_artista aa
ON aa.album_id = al.id
INNER JOIN artista ar
ON ar.id = aa.artista_id;
```

---

# Exercício 20

## SCHEMAS

Crie um schema chamado **backup** e mova a tabela **playlist** para esse schema.

## Gabarito

```sql
CREATE SCHEMA backup;
```

```sql
ALTER TABLE playlist
SET SCHEMA backup;
```

Para consultar posteriormente:

```sql
SELECT *
FROM backup.playlist;
```

---

# Apostila de SQL PostgreSQL – Exercícios

# Parte 2 – Exercícios 21 a 40

**Tema:** LEFT JOIN, RIGHT JOIN, GROUP BY e Funções de Agregação

Base de dados: **spoti_pobre**. 

---

# Exercício 21

Liste todos os usuários e suas playlists. Usuários sem playlist também devem aparecer.

## Gabarito

```sql
SELECT
    u.nome,
    p.nome AS playlist
FROM usuario u
LEFT JOIN usuario_playlist up
       ON up.usuario_id = u.id
LEFT JOIN playlist p
       ON p.id = up.playlist_id
ORDER BY u.nome;
```

---

# Exercício 22

Liste todas as playlists e seus respectivos donos. Caso alguma playlist não possua dono, ela também deve aparecer.

## Gabarito

```sql
SELECT
    p.nome,
    u.nome AS dono
FROM playlist p
LEFT JOIN usuario_playlist up
       ON up.playlist_id = p.id
      AND up.dono = TRUE
LEFT JOIN usuario u
       ON u.id = up.usuario_id;
```

---

# Exercício 23

Liste todas as músicas e informe em qual playlist elas aparecem.

## Gabarito

```sql
SELECT
    m.nome,
    p.nome AS playlist
FROM musica m
LEFT JOIN playlist_musica pm
       ON pm.musica_id = m.id
LEFT JOIN playlist p
       ON p.id = pm.playlist_id
ORDER BY m.nome;
```

---

# Exercício 24

Liste todos os artistas e seus álbuns.

## Gabarito

```sql
SELECT
    ar.nome,
    al.titulo
FROM artista ar
LEFT JOIN album_artista aa
       ON aa.artista_id = ar.id
LEFT JOIN album al
       ON al.id = aa.album_id;
```

---

# Exercício 25

Liste todos os gêneros e seus respectivos álbuns.

## Gabarito

```sql
SELECT
    g.nome,
    a.titulo
FROM genero g
LEFT JOIN album a
       ON a.genero_id = g.id;
```

---

# Exercício 26

Liste todas as músicas e informe se já foram reproduzidas por algum usuário.

## Gabarito

```sql
SELECT
    m.nome,
    r.usuario_id
FROM musica m
LEFT JOIN reproducao r
       ON r.musica_id = m.id;
```

---

# Exercício 27

Liste todos os usuários e as músicas reproduzidas.

## Gabarito

```sql
SELECT
    u.nome,
    m.nome AS musica
FROM usuario u
LEFT JOIN reproducao r
       ON r.usuario_id = u.id
LEFT JOIN musica m
       ON m.id = r.musica_id;
```

---

# Exercício 28

Liste todos os artistas e as músicas gravadas por eles.

## Gabarito

```sql
SELECT
    ar.nome,
    m.nome
FROM artista ar
LEFT JOIN album_artista aa
       ON aa.artista_id = ar.id
LEFT JOIN album_musica am
       ON am.album_id = aa.album_id
LEFT JOIN musica m
       ON m.id = am.musica_id;
```

---

# Exercício 29

Liste todas as playlists e a quantidade de músicas que possuem.

## Gabarito

```sql
SELECT
    p.nome,
    COUNT(pm.musica_id) AS quantidade
FROM playlist p
LEFT JOIN playlist_musica pm
       ON pm.playlist_id = p.id
GROUP BY p.nome
ORDER BY quantidade DESC;
```

---

# Exercício 30

Liste todos os usuários e quantas playlists participam.

## Gabarito

```sql
SELECT
    u.nome,
    COUNT(up.playlist_id) AS total
FROM usuario u
LEFT JOIN usuario_playlist up
       ON up.usuario_id = u.id
GROUP BY u.nome
ORDER BY total DESC;
```

---

# Exercício 31

Utilize **RIGHT JOIN** para listar todos os usuários associados às playlists.

## Gabarito

```sql
SELECT
    u.nome,
    p.nome
FROM usuario_playlist up
RIGHT JOIN usuario u
       ON u.id = up.usuario_id
LEFT JOIN playlist p
       ON p.id = up.playlist_id;
```

---

# Exercício 32

Utilize **RIGHT JOIN** para listar todas as músicas e suas reproduções.

## Gabarito

```sql
SELECT
    m.nome,
    r.quando
FROM reproducao r
RIGHT JOIN musica m
       ON m.id = r.musica_id;
```

---

# Exercício 33

Conte quantos usuários existem.

## Gabarito

```sql
SELECT COUNT(*) AS total_usuarios
FROM usuario;
```

---

# Exercício 34

Conte quantas playlists existem.

## Gabarito

```sql
SELECT COUNT(*) AS total_playlists
FROM playlist;
```

---

# Exercício 35

Conte quantas músicas existem em cada álbum.

## Gabarito

```sql
SELECT
    a.titulo,
    COUNT(am.musica_id) AS musicas
FROM album a
LEFT JOIN album_musica am
       ON am.album_id = a.id
GROUP BY a.titulo;
```

---

# Exercício 36

Conte quantos álbuns cada artista possui.

## Gabarito

```sql
SELECT
    ar.nome,
    COUNT(aa.album_id) AS albuns
FROM artista ar
LEFT JOIN album_artista aa
       ON aa.artista_id = ar.id
GROUP BY ar.nome
ORDER BY albuns DESC;
```

---

# Exercício 37

Conte quantas playlists existem por usuário.

## Gabarito

```sql
SELECT
    u.nome,
    COUNT(up.playlist_id)
FROM usuario u
LEFT JOIN usuario_playlist up
       ON up.usuario_id = u.id
GROUP BY u.nome;
```

---

# Exercício 38

Calcule a duração média das músicas.

## Gabarito

```sql
SELECT AVG(duracao)
FROM musica;
```

---

# Exercício 39

Mostre a menor, a maior e a média duração das músicas.

## Gabarito

```sql
SELECT
    MIN(duracao),
    MAX(duracao),
    AVG(duracao)
FROM musica;
```

---

# Exercício 40

Monte um relatório contendo:

* Nome do artista
* Quantidade de álbuns
* Quantidade de músicas
* Quantidade de gêneros diferentes

Ordene do artista com maior quantidade de músicas para o menor.

## Gabarito

```sql
SELECT
    ar.nome,
    COUNT(DISTINCT aa.album_id) AS albuns,
    COUNT(DISTINCT am.musica_id) AS musicas,
    COUNT(DISTINCT al.genero_id) AS generos
FROM artista ar
LEFT JOIN album_artista aa
       ON aa.artista_id = ar.id
LEFT JOIN album al
       ON al.id = aa.album_id
LEFT JOIN album_musica am
       ON am.album_id = al.id
GROUP BY ar.nome
ORDER BY musicas DESC;
```

---

# Apostila de SQL PostgreSQL – Exercícios

# Parte 3 – Exercícios 41 a 60

**Tema:** HAVING, Manipulação de Datas e Manipulação de Strings

Base de dados: **spoti_pobre**. 

---

# Exercício 41

Liste os artistas que possuem mais de um álbum cadastrado.

## Gabarito

```sql
SELECT
    ar.nome,
    COUNT(*) AS quantidade
FROM artista ar
JOIN album_artista aa
ON aa.artista_id = ar.id
GROUP BY ar.nome
HAVING COUNT(*) > 1;
```

---

# Exercício 42

Liste os usuários que participam de mais de uma playlist.

## Gabarito

```sql
SELECT
    u.nome,
    COUNT(*) AS total
FROM usuario u
JOIN usuario_playlist up
ON up.usuario_id = u.id
GROUP BY u.nome
HAVING COUNT(*) > 1;
```

---

# Exercício 43

Liste os artistas que possuem pelo menos duas músicas cadastradas.

## Gabarito

```sql
SELECT
    ar.nome,
    COUNT(m.id) AS musicas
FROM artista ar
JOIN album_artista aa
ON aa.artista_id = ar.id
JOIN album_musica am
ON am.album_id = aa.album_id
JOIN musica m
ON m.id = am.musica_id
GROUP BY ar.nome
HAVING COUNT(m.id) >= 2;
```

---

# Exercício 44

Liste as playlists que possuem mais de duas músicas.

## Gabarito

```sql
SELECT
    p.nome,
    COUNT(*) AS quantidade
FROM playlist p
JOIN playlist_musica pm
ON pm.playlist_id = p.id
GROUP BY p.nome
HAVING COUNT(*) > 2;
```

---

# Exercício 45

Liste os gêneros que possuem mais de um álbum.

## Gabarito

```sql
SELECT
    g.nome,
    COUNT(*) AS albuns
FROM genero g
JOIN album a
ON a.genero_id = g.id
GROUP BY g.nome
HAVING COUNT(*) > 1;
```

---

# Exercício 46

Mostre o ano de nascimento de cada usuário.

## Gabarito

```sql
SELECT
    nome,
    EXTRACT(YEAR FROM data_nascimento) AS ano
FROM usuario;
```

---

# Exercício 47

Mostre o mês de criação de cada playlist.

## Gabarito

```sql
SELECT
    nome,
    EXTRACT(MONTH FROM data_hora_criacao) AS mes
FROM playlist;
```

---

# Exercício 48

Mostre o dia da semana em que cada reprodução ocorreu.

## Gabarito

```sql
SELECT
    usuario_id,
    musica_id,
    EXTRACT(DOW FROM quando) AS dia_semana
FROM reproducao;
```

---

# Exercício 49

Liste todas as músicas lançadas na década de 1970.

## Gabarito

```sql
SELECT
    nome
FROM musica
WHERE EXTRACT(YEAR FROM data_hora_lancamento)
BETWEEN 1970 AND 1979;
```

---

# Exercício 50

Mostre quantos anos cada álbum possui.

## Gabarito

```sql
SELECT
    titulo,
    AGE(data_lancamento)
FROM album;
```

---

# Exercício 51

Mostre somente o ano de lançamento dos álbuns.

## Gabarito

```sql
SELECT
    titulo,
    DATE_TRUNC('year', data_lancamento)
FROM album;
```

---

# Exercício 52

Liste os usuários em letras maiúsculas.

## Gabarito

```sql
SELECT
    UPPER(nome)
FROM usuario;
```

---

# Exercício 53

Liste os artistas em letras minúsculas.

## Gabarito

```sql
SELECT
    LOWER(nome)
FROM artista;
```

---

# Exercício 54

Liste os gêneros com apenas a primeira letra de cada palavra maiúscula.

## Gabarito

```sql
SELECT
    INITCAP(nome)
FROM genero;
```

---

# Exercício 55

Crie uma coluna contendo:

```
Nome - Email
```

## Gabarito

```sql
SELECT
    CONCAT(nome, ' - ', email)
FROM usuario;
```

---

# Exercício 56

Mostre apenas os cinco primeiros caracteres do nome das músicas.

## Gabarito

```sql
SELECT
    SUBSTRING(nome FROM 1 FOR 5)
FROM musica;
```

---

# Exercício 57

Substitua a palavra "Brasil" por "BR" nos nomes das playlists.

## Gabarito

```sql
SELECT
    REPLACE(nome, 'Brasil', 'BR')
FROM playlist;
```

---

# Exercício 58

Mostre o tamanho do nome de cada artista.

## Gabarito

```sql
SELECT
    nome,
    LENGTH(nome)
FROM artista;
```

---

# Exercício 59

Remova espaços em branco antes e depois do texto `' PostgreSQL '`.

## Gabarito

```sql
SELECT
    TRIM(' PostgreSQL ');
```

---

# Exercício 60

Construa um relatório contendo:

* Nome do artista em maiúsculas;
* Ano do álbum;
* Quantidade de músicas;
* Somente artistas com mais de uma música;
* Ordenado pela quantidade de músicas.

## Gabarito

```sql
SELECT
    UPPER(ar.nome) AS artista,
    EXTRACT(YEAR FROM al.data_lancamento) AS ano,
    COUNT(am.musica_id) AS musicas
FROM artista ar
JOIN album_artista aa
ON aa.artista_id = ar.id
JOIN album al
ON al.id = aa.album_id
JOIN album_musica am
ON am.album_id = al.id
GROUP BY
    ar.nome,
    al.data_lancamento
HAVING COUNT(am.musica_id) > 1
ORDER BY musicas DESC;
```

---

# Apostila de SQL PostgreSQL – Exercícios

# Parte 4 – Exercícios 61 a 80

**Tema:** Subconsultas (Subselects), EXISTS, IN, ANY, ALL, CTEs e Views

Base de dados: **spoti_pobre**. 

---

# Exercício 61

Liste os usuários que possuem pelo menos uma reprodução cadastrada utilizando `IN`.

## Gabarito

```sql
SELECT nome
FROM usuario
WHERE id IN (
    SELECT usuario_id
    FROM reproducao
);
```

---

# Exercício 62

Liste as músicas que pertencem a algum álbum utilizando `IN`.

## Gabarito

```sql
SELECT nome
FROM musica
WHERE id IN (
    SELECT musica_id
    FROM album_musica
);
```

---

# Exercício 63

Liste os artistas que possuem pelo menos um álbum lançado antes da média de lançamento de todos os álbuns.

## Gabarito

```sql
SELECT DISTINCT ar.nome
FROM artista ar
JOIN album_artista aa
ON aa.artista_id = ar.id
JOIN album al
ON al.id = aa.album_id
WHERE al.data_lancamento <
(
    SELECT AVG(data_lancamento)
    FROM album
);
```

---

# Exercício 64

Liste os usuários que nunca reproduziram nenhuma música utilizando `NOT IN`.

## Gabarito

```sql
SELECT nome
FROM usuario
WHERE id NOT IN (
    SELECT usuario_id
    FROM reproducao
);
```

---

# Exercício 65

Liste as playlists que possuem pelo menos uma música.

## Gabarito

```sql
SELECT nome
FROM playlist
WHERE id IN (
    SELECT playlist_id
    FROM playlist_musica
);
```

---

# Exercício 66

Liste os artistas que possuem álbuns do gênero MPB.

## Gabarito

```sql
SELECT DISTINCT ar.nome
FROM artista ar
JOIN album_artista aa
ON aa.artista_id = ar.id
WHERE aa.album_id IN
(
    SELECT id
    FROM album
    WHERE genero_id =
    (
        SELECT id
        FROM genero
        WHERE nome='MPB'
    )
);
```

---

# Exercício 67

Utilize `EXISTS` para listar os usuários que possuem reproduções.

## Gabarito

```sql
SELECT u.nome
FROM usuario u
WHERE EXISTS
(
    SELECT 1
    FROM reproducao r
    WHERE r.usuario_id=u.id
);
```

---

# Exercício 68

Utilize `NOT EXISTS` para listar os usuários que nunca ouviram músicas.

## Gabarito

```sql
SELECT u.nome
FROM usuario u
WHERE NOT EXISTS
(
    SELECT 1
    FROM reproducao r
    WHERE r.usuario_id=u.id
);
```

---

# Exercício 69

Liste as playlists que possuem mais músicas que a média de músicas por playlist.

## Gabarito

```sql
SELECT
    p.nome,
    COUNT(*) quantidade
FROM playlist p
JOIN playlist_musica pm
ON pm.playlist_id=p.id
GROUP BY p.id,p.nome
HAVING COUNT(*) >
(
    SELECT AVG(qtd)
    FROM
    (
        SELECT COUNT(*) qtd
        FROM playlist_musica
        GROUP BY playlist_id
    ) x
);
```

---

# Exercício 70

Liste os álbuns cuja data de lançamento é igual à menor data cadastrada.

## Gabarito

```sql
SELECT titulo
FROM album
WHERE data_lancamento=
(
    SELECT MIN(data_lancamento)
    FROM album
);
```

---

# Exercício 71

Utilize `ANY` para listar músicas com duração maior que pelo menos uma música do álbum 3.

## Gabarito

```sql
SELECT nome
FROM musica
WHERE duracao >
ANY
(
    SELECT m.duracao
    FROM musica m
    JOIN album_musica am
    ON am.musica_id=m.id
    WHERE am.album_id=3
);
```

---

# Exercício 72

Utilize `ALL` para listar músicas maiores que todas as músicas do álbum 3.

## Gabarito

```sql
SELECT nome
FROM musica
WHERE duracao >
ALL
(
    SELECT m.duracao
    FROM musica m
    JOIN album_musica am
    ON am.musica_id=m.id
    WHERE am.album_id=3
);
```

---

# Exercício 73

Crie uma CTE contendo todos os artistas e seus álbuns.

## Gabarito

```sql
WITH artistas_album AS
(
    SELECT
        ar.nome artista,
        al.titulo
    FROM artista ar
    JOIN album_artista aa
    ON aa.artista_id=ar.id
    JOIN album al
    ON al.id=aa.album_id
)
SELECT *
FROM artistas_album;
```

---

# Exercício 74

Crie uma CTE contendo a quantidade de músicas por playlist.

## Gabarito

```sql
WITH qtd_playlist AS
(
    SELECT
        playlist_id,
        COUNT(*) quantidade
    FROM playlist_musica
    GROUP BY playlist_id
)
SELECT *
FROM qtd_playlist;
```

---

# Exercício 75

Crie uma View chamada **vw_artistas** contendo:

* artista
* álbum
* gênero

## Gabarito

```sql
CREATE VIEW vw_artistas AS

SELECT
    ar.nome artista,
    al.titulo album,
    g.nome genero
FROM artista ar
JOIN album_artista aa
ON aa.artista_id=ar.id
JOIN album al
ON al.id=aa.album_id
JOIN genero g
ON g.id=al.genero_id;
```

---

# Exercício 76

Consulte a View criada anteriormente.

## Gabarito

```sql
SELECT *
FROM vw_artistas;
```

---

# Exercício 77

Crie uma View contendo todas as reproduções.

Campos:

* usuário
* música
* data

## Gabarito

```sql
CREATE VIEW vw_reproducoes AS

SELECT
    u.nome usuario,
    m.nome musica,
    r.quando
FROM reproducao r
JOIN usuario u
ON u.id=r.usuario_id
JOIN musica m
ON m.id=r.musica_id;
```

---

# Exercício 78

Crie uma View contendo:

* Playlist
* Quantidade de músicas

## Gabarito

```sql
CREATE VIEW vw_playlists AS

SELECT
    p.nome,
    COUNT(pm.musica_id) quantidade
FROM playlist p
LEFT JOIN playlist_musica pm
ON pm.playlist_id=p.id
GROUP BY p.nome;
```

---

# Exercício 79

Utilize uma View para listar apenas playlists com mais de duas músicas.

## Gabarito

```sql
SELECT *
FROM vw_playlists
WHERE quantidade>2;
```

---

# Exercício 80

Crie uma CTE que calcule:

* artista
* quantidade de álbuns
* quantidade de músicas

Depois liste apenas artistas com pelo menos um álbum e duas músicas.

## Gabarito

```sql
WITH relatorio AS
(
    SELECT
        ar.nome,
        COUNT(DISTINCT aa.album_id) albuns,
        COUNT(DISTINCT am.musica_id) musicas
    FROM artista ar
    LEFT JOIN album_artista aa
    ON aa.artista_id=ar.id
    LEFT JOIN album_musica am
    ON am.album_id=aa.album_id
    GROUP BY ar.nome
)

SELECT *
FROM relatorio
WHERE albuns>=1
AND musicas>=2
ORDER BY musicas DESC;
```

---
# Apostila de SQL PostgreSQL – Exercícios

# Parte 5 – Exercícios 81 a 100

**Tema:** `STRING_AGG`, Relatórios Avançados, Views, CTEs e Desafios Integradores

Base de dados: **spoti_pobre**. 

---

# Exercício 81

Liste cada álbum juntamente com todas as suas músicas em uma única linha utilizando `STRING_AGG`.

## Gabarito

```sql
SELECT
    a.titulo,
    STRING_AGG(m.nome, ', ' ORDER BY m.nome) AS musicas
FROM album a
JOIN album_musica am
ON am.album_id = a.id
JOIN musica m
ON m.id = am.musica_id
GROUP BY a.titulo
ORDER BY a.titulo;
```

---

# Exercício 82

Liste cada playlist juntamente com todas as músicas separadas por vírgula.

## Gabarito

```sql
SELECT
    p.nome,
    STRING_AGG(m.nome, ', ' ORDER BY m.nome) AS musicas
FROM playlist p
JOIN playlist_musica pm
ON pm.playlist_id = p.id
JOIN musica m
ON m.id = pm.musica_id
GROUP BY p.nome
ORDER BY p.nome;
```

---

# Exercício 83

Liste cada artista juntamente com todos os seus álbuns utilizando `STRING_AGG`.

## Gabarito

```sql
SELECT
    ar.nome,
    STRING_AGG(al.titulo, ', ' ORDER BY al.data_lancamento) AS albuns
FROM artista ar
JOIN album_artista aa
ON aa.artista_id = ar.id
JOIN album al
ON al.id = aa.album_id
GROUP BY ar.nome
ORDER BY ar.nome;
```

---

# Exercício 84

Liste cada gênero e todos os álbuns pertencentes a ele.

## Gabarito

```sql
SELECT
    g.nome,
    STRING_AGG(a.titulo, ', ' ORDER BY a.titulo) AS albuns
FROM genero g
LEFT JOIN album a
ON a.genero_id = g.id
GROUP BY g.nome
ORDER BY g.nome;
```

---

# Exercício 85

Liste cada usuário e todas as playlists das quais participa.

## Gabarito

```sql
SELECT
    u.nome,
    STRING_AGG(p.nome, ', ' ORDER BY p.nome) AS playlists
FROM usuario u
LEFT JOIN usuario_playlist up
ON up.usuario_id = u.id
LEFT JOIN playlist p
ON p.id = up.playlist_id
GROUP BY u.nome
ORDER BY u.nome;
```

---

# Exercício 86

Construa um relatório contendo:

* Artista
* Álbum
* Quantidade de músicas
* Duração média das músicas

## Gabarito

```sql
SELECT
    ar.nome,
    al.titulo,
    COUNT(m.id) AS musicas,
    AVG(m.duracao) AS duracao_media
FROM artista ar
JOIN album_artista aa
ON aa.artista_id = ar.id
JOIN album al
ON al.id = aa.album_id
JOIN album_musica am
ON am.album_id = al.id
JOIN musica m
ON m.id = am.musica_id
GROUP BY
    ar.nome,
    al.titulo
ORDER BY ar.nome;
```

---

# Exercício 87

Liste os cinco artistas com maior quantidade de músicas.

## Gabarito

```sql
SELECT
    ar.nome,
    COUNT(m.id) AS total
FROM artista ar
JOIN album_artista aa
ON aa.artista_id = ar.id
JOIN album_musica am
ON am.album_id = aa.album_id
JOIN musica m
ON m.id = am.musica_id
GROUP BY ar.nome
ORDER BY total DESC
LIMIT 5;
```

---

# Exercício 88

Liste as cinco playlists com maior quantidade de músicas.

## Gabarito

```sql
SELECT
    p.nome,
    COUNT(pm.musica_id) AS total
FROM playlist p
LEFT JOIN playlist_musica pm
ON pm.playlist_id = p.id
GROUP BY p.nome
ORDER BY total DESC
LIMIT 5;
```

---

# Exercício 89

Liste os usuários que ouviram músicas do gênero MPB.

## Gabarito

```sql
SELECT DISTINCT
    u.nome
FROM usuario u
JOIN reproducao r
ON r.usuario_id = u.id
JOIN musica m
ON m.id = r.musica_id
JOIN album_musica am
ON am.musica_id = m.id
JOIN album a
ON a.id = am.album_id
JOIN genero g
ON g.id = a.genero_id
WHERE g.nome = 'MPB';
```

---

# Exercício 90

Liste todos os artistas que possuem músicas em playlists.

## Gabarito

```sql
SELECT DISTINCT
    ar.nome
FROM artista ar
JOIN album_artista aa
ON aa.artista_id = ar.id
JOIN album_musica am
ON am.album_id = aa.album_id
JOIN playlist_musica pm
ON pm.musica_id = am.musica_id
ORDER BY ar.nome;
```

---

# Exercício 91

Crie uma CTE contendo todas as reproduções realizadas em julho de 2026.

## Gabarito

```sql
WITH reproducoes_julho AS
(
    SELECT *
    FROM reproducao
    WHERE quando BETWEEN
          '2026-07-01'
      AND '2026-07-31 23:59:59'
)
SELECT *
FROM reproducoes_julho;
```

---

# Exercício 92

Crie uma View contendo:

* usuário
* quantidade de reproduções

## Gabarito

```sql
CREATE VIEW vw_total_reproducoes AS

SELECT
    u.nome,
    COUNT(r.musica_id) AS total
FROM usuario u
LEFT JOIN reproducao r
ON r.usuario_id = u.id
GROUP BY u.nome;
```

---

# Exercício 93

Utilize a View anterior para listar apenas usuários com mais de uma reprodução.

## Gabarito

```sql
SELECT *
FROM vw_total_reproducoes
WHERE total > 1;
```

---

# Exercício 94

Crie um relatório contendo:

* usuário
* playlist
* quantidade de músicas da playlist

## Gabarito

```sql
SELECT
    u.nome,
    p.nome,
    COUNT(pm.musica_id) AS musicas
FROM usuario u
JOIN usuario_playlist up
ON up.usuario_id = u.id
JOIN playlist p
ON p.id = up.playlist_id
LEFT JOIN playlist_musica pm
ON pm.playlist_id = p.id
GROUP BY
    u.nome,
    p.nome
ORDER BY u.nome;
```

---

# Exercício 95

Liste os artistas que possuem músicas com duração superior à média de duração de todas as músicas.

## Gabarito

```sql
SELECT DISTINCT
    ar.nome
FROM artista ar
JOIN album_artista aa
ON aa.artista_id = ar.id
JOIN album_musica am
ON am.album_id = aa.album_id
JOIN musica m
ON m.id = am.musica_id
WHERE m.duracao >
(
    SELECT AVG(duracao)
    FROM musica
);
```

---

# Exercício 96

Liste os usuários que ouviram músicas lançadas antes de 1980.

## Gabarito

```sql
SELECT DISTINCT
    u.nome
FROM usuario u
JOIN reproducao r
ON r.usuario_id = u.id
JOIN musica m
ON m.id = r.musica_id
WHERE m.data_hora_lancamento < '1980-01-01';
```

---

# Exercício 97

Monte um relatório contendo:

* artista
* gênero
* quantidade de álbuns
* quantidade de músicas

## Gabarito

```sql
SELECT
    ar.nome,
    g.nome,
    COUNT(DISTINCT a.id) AS albuns,
    COUNT(DISTINCT m.id) AS musicas
FROM artista ar
JOIN album_artista aa
ON aa.artista_id = ar.id
JOIN album a
ON a.id = aa.album_id
JOIN genero g
ON g.id = a.genero_id
JOIN album_musica am
ON am.album_id = a.id
JOIN musica m
ON m.id = am.musica_id
GROUP BY
    ar.nome,
    g.nome
ORDER BY ar.nome;
```

---

# Exercício 98

Monte uma consulta que mostre:

* Playlist
* Dono
* Colaboradores

Os colaboradores devem aparecer em uma única coluna utilizando `STRING_AGG`.

## Gabarito

```sql
SELECT
    p.nome,
    dono.nome AS dono,
    STRING_AGG(colab.nome, ', ' ORDER BY colab.nome) AS colaboradores
FROM playlist p
JOIN usuario_playlist upd
ON upd.playlist_id = p.id
AND upd.dono = TRUE
JOIN usuario dono
ON dono.id = upd.usuario_id
LEFT JOIN usuario_playlist upc
ON upc.playlist_id = p.id
AND upc.colaborador = TRUE
LEFT JOIN usuario colab
ON colab.id = upc.usuario_id
GROUP BY
    p.nome,
    dono.nome;
```

---

# Exercício 99

Crie uma View contendo todas as informações das músicas:

* música
* artista
* álbum
* gênero
* duração

## Gabarito

```sql
CREATE VIEW vw_catalogo AS

SELECT
    m.nome AS musica,
    ar.nome AS artista,
    a.titulo AS album,
    g.nome AS genero,
    m.duracao
FROM musica m
JOIN album_musica am
ON am.musica_id = m.id
JOIN album a
ON a.id = am.album_id
JOIN genero g
ON g.id = a.genero_id
JOIN album_artista aa
ON aa.album_id = a.id
JOIN artista ar
ON ar.id = aa.artista_id;
```

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

## Gabarito

```sql
WITH estatisticas AS (
    SELECT
        ar.id,
        ar.nome AS artista,
        g.nome AS genero,
        COUNT(DISTINCT a.id) AS albuns,
        COUNT(DISTINCT m.id) AS musicas,
        AVG(m.duracao) AS duracao_media,
        STRING_AGG(DISTINCT a.titulo, ', ' ORDER BY a.titulo) AS lista_albuns,
        MIN(EXTRACT(YEAR FROM a.data_lancamento)) AS primeiro_album,
        MAX(EXTRACT(YEAR FROM a.data_lancamento)) AS ultimo_album
    FROM artista ar
    JOIN album_artista aa
        ON aa.artista_id = ar.id
    JOIN album a
        ON a.id = aa.album_id
    JOIN genero g
        ON g.id = a.genero_id
    JOIN album_musica am
        ON am.album_id = a.id
    JOIN musica m
        ON m.id = am.musica_id
    GROUP BY
        ar.id,
        ar.nome,
        g.nome
)

SELECT *
FROM estatisticas
WHERE albuns >= 1
ORDER BY musicas DESC, artista;
```

---



