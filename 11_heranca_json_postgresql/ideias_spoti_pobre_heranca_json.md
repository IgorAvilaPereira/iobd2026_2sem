## 1. Herança de tabelas no PostgreSQL

A base já possui as tabelas `usuario`, `artista`, `musica`, `album` e `playlist`. Isso permite demonstrar herança usando `INHERITS`, recurso específico do PostgreSQL.

### Exemplo A — Usuários comuns e usuários premium

Imagine que a plataforma passe a oferecer assinaturas premium, com data de vencimento e valor mensal. Podemos criar uma tabela especializada que herda os atributos de `usuario`.

```
CREATE TABLE usuario_premium (
    plano text NOT NULL DEFAULT 'individual',
    valor_mensal numeric(8,2) NOT NULL DEFAULT 19.90,
    validade date
) INHERITS (usuario);
```

A tabela `usuario_premium` herda as colunas de `usuario`:

- `id`
- `email`
- `senha`
- `nome`
- `data_nascimento`

E acrescenta `plano`, `valor_mensal` e `validade`.

Inserindo um usuário premium:

```
INSERT INTO usuario_premium
    (email, senha, nome, data_nascimento,
     plano, valor_mensal, validade)
VALUES
    ('premium@email.com', 'hash123', 'Usuário Premium',
     '2000-05-10', 'familia', 34.90, '2027-05-10');
```

Consultando os usuários:

```
SELECT id, nome, email
FROM usuario;
```

Ponto didático importante: no PostgreSQL, uma consulta à tabela-pai `usuario` inclui, por padrão, as linhas das tabelas descendentes. Assim, o usuário premium também aparece nessa consulta, mesmo que tenha sido inserido em `usuario_premium`.

Para consultar somente as linhas armazenadas diretamente na tabela-pai:

```
SELECT id, nome, email
FROM ONLY usuario;
```

E para consultar somente os usuários premium:

```
SELECT id, nome, plano, valor_mensal
FROM usuario_premium;
```

### Exemplo B — Herança para diferentes tipos de conteúdo musical

Outra possibilidade é aproveitar `musica` como tabela-pai e criar uma categoria de música ao vivo com informações adicionais, como local e público.

```
CREATE TABLE musica_ao_vivo (
    local_gravacao text,
    publico_estimado integer
) INHERITS (musica);
```

Inserindo uma música ao vivo:

```
INSERT INTO musica_ao_vivo
    (nome, duracao, local_gravacao, publico_estimado)
VALUES
    ('Samba ao Vivo', '00:05:30',
     'Porto Alegre', 2500);
```

Consultando todas as músicas, inclusive as da tabela descendente:

```
SELECT tableoid::regclass AS tabela_origem,
       id, nome, duracao
FROM musica;
```

O `tableoid` permite identificar de qual tabela veio cada linha. Isso é ótimo para demonstrar que a herança do PostgreSQL é diferente da herança de classes em Java.

Cuidado importante: a herança do PostgreSQL não equivale a uma especialização relacional completa. Chaves primárias, restrições `UNIQUE` e chaves estrangeiras não são herdadas como se fossem uma única estrutura global. Nesse exemplo, o `id` herdado não ganha automaticamente uma chave primária na tabela filha, e sua sequência também não garante unicidade entre as tabelas. Para uma atividade didática, vale explorar essa limitação; para um projeto real, é preciso planejar a identidade dos registros e os relacionamentos.

## 2. Colunas JSONB na base musical

Aqui vejo ainda mais possibilidades. A plataforma tem atributos relativamente estáveis, como o nome da música e sua duração, mas também pode ter metadados opcionais que variam de uma música para outra.

### Exemplo A — Metadados de músicas

Adicione uma coluna JSONB à tabela `musica`:

```
ALTER TABLE musica
ADD COLUMN metadados jsonb;
```

Registre informações que não existem nas colunas tradicionais:

```
UPDATE musica
SET metadados = '{
    "idioma": "pt-BR",
    "explicit": false,
    "bpm": 120,
    "instrumentos": ["violão", "baixo", "bateria"],
    "versao": "remasterizada"
}'::jsonb
WHERE id = 1;
```

Agora podemos consultar atributos específicos dentro do documento JSON:

```
SELECT nome,
       metadados ->> 'idioma' AS idioma,
       metadados ->> 'bpm' AS bpm
FROM musica;
```

Filtrar músicas por um atributo JSON:

```
SELECT nome
FROM musica
WHERE metadados ->> 'idioma' = 'pt-BR';
```

Ou localizar músicas que contenham determinado instrumento:

```
SELECT nome
FROM musica
WHERE metadados -> 'instrumentos' ? 'violão';
```

Esses exemplos trabalham operadores de acesso ao JSON, extração de valores e filtros.

### Exemplo B — Metadados de álbuns

A tabela `album` já tem título, data de lançamento, gênero e capa. Poderíamos adicionar informações complementares e variáveis:

```
ALTER TABLE album
ADD COLUMN informacoes jsonb;
```

Exemplo de preenchimento:

```
UPDATE album
SET informacoes = '{
    "gravadora": "Som Brasileiro",
    "formato": ["LP", "CD", "digital"],
    "premios": ["Melhor Álbum"],
    "edicao": {
        "numero": 2,
        "especial": true
    }
}'::jsonb
WHERE id = 1;
```

Consultando campos aninhados:

```
SELECT titulo,
       informacoes ->> 'gravadora' AS gravadora,
       informacoes -> 'edicao' ->> 'numero' AS edicao
FROM album;
```

Também dá para pesquisar álbuns por um valor booleano dentro do documento:

```
SELECT titulo
FROM album
WHERE informacoes -> 'edicao' ->> 'especial' = 'true';
```

### Exemplo C — JSONB para registrar contexto das reproduções

A tabela `reproducao` já registra usuário, música e instante da reprodução. Podemos adicionar dados contextuais sem criar várias tabelas auxiliares.

```
ALTER TABLE reproducao
ADD COLUMN contexto jsonb;
```

Exemplo:

```
UPDATE reproducao
SET contexto = '{
    "dispositivo": "celular",
    "qualidade": "alta",
    "origem": "playlist",
    "offline": false
}'::jsonb
WHERE id = 1;
```

Consulta:

```
SELECT usuario_id,
       musica_id,
       contexto ->> 'dispositivo' AS dispositivo,
       contexto ->> 'origem' AS origem
FROM reproducao
WHERE contexto ->> 'dispositivo' = 'celular';
```

Isso permite discutir quando vale a pena ter uma coluna tradicional e quando um documento flexível é mais conveniente.

## 3. Índices para pesquisas em JSONB

Depois de ensinar consultas, vale mostrar que JSONB também pode ser indexado.

```
CREATE INDEX idx_musica_metadados
ON musica USING GIN (metadados);
```

Esse índice é especialmente útil para consultas de contenção, por exemplo:

```
SELECT nome
FROM musica
WHERE metadados @> '{"explicit": false}'::jsonb;
```

O operador `@>` verifica se o documento contém a estrutura indicada. É uma boa oportunidade para comparar índices tradicionais com índices GIN.

## 4. Uma atividade prática que combina os dois assuntos

## Proposta: evolução do Spoti Pobre

Nível intermediário — PostgreSQL

Os alunos deverão estender a base existente para atender a novos requisitos da plataforma.

Parte 1 — Herança

- Criar `usuario_premium` herdando de `usuario`.
- Acrescentar atributos específicos de assinatura.
- Inserir usuários nas tabelas pai e filha.
- Comparar consultas com e sem `ONLY`.
- Investigar o comportamento de chaves primárias, sequências e restrições.

Parte 2 — JSONB

- Adicionar metadados a `musica`, `album` e `reproducao`.
- Inserir documentos com strings, números, booleanos, arrays e objetos aninhados.
- Consultar valores usando `->` e `->>`.
- Filtrar documentos com `?` e `@>`.
- Criar um índice GIN e discutir quais consultas podem se beneficiar dele.

Parte 3 — Comparação

- Comparar atributos representados por colunas tradicionais e por JSONB.
- Identificar quais dados precisam de integridade referencial.
- Justificar quando usar uma tabela relacionada, herança ou um documento JSON.


Um detalhe importante para a discussão: JSONB não deve substituir automaticamente as tabelas relacionais. Artistas, álbuns, músicas, usuários e reproduções têm relacionamentos e restrições que fazem sentido manter no modelo relacional. JSONB é mais interessante para atributos complementares, flexíveis ou que variam conforme o tipo de registro.
