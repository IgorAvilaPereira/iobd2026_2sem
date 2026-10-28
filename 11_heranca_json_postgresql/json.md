# JSON no PostgreSQL

---

## 🧱 1. **Tipos JSON no PostgreSQL**

O PostgreSQL tem **dois tipos** principais pra armazenar dados JSON:

| Tipo    | Descrição                                                             |
| ------- | --------------------------------------------------------------------- |
| `json`  | Armazena o texto JSON como foi inserido (sem otimização).             |
| `jsonb` | Armazena o JSON em formato binário, otimizado para busca e indexação. |

👉 **Recomendação:** use **`jsonb`** quase sempre — ele é mais rápido, ocupa menos espaço e permite índices.

---

## 🧩 2. **Criando uma tabela com JSON**

```sql
CREATE TABLE produtos (
    id SERIAL PRIMARY KEY,
    nome TEXT,
    dados JSONB
);
```

Inserindo dados:

```sql
INSERT INTO produtos (nome, dados)
VALUES
  ('Notebook', '{"marca": "Dell", "preco": 5500, "estoque": {"quantidade": 20, "local": "SP"}}'),
  ('Mouse', '{"marca": "Logitech", "preco": 150}');
```

---

## 🔍 3. **Consultando campos JSON**

Você pode usar **operadores** para acessar o conteúdo:

| Operador | Retorno                  | Exemplo                            | Resultado |
| -------- | ------------------------ | ---------------------------------- | --------- |
| `->`     | JSON                     | `dados -> 'marca'`                 | `"Dell"`  |
| `->>`    | Texto                    | `dados ->> 'marca'`                | `Dell`    |
| `#>`     | JSON (níveis aninhados)  | `dados #> '{estoque,local}'`       | `"SP"`    |
| `#>>`    | Texto (níveis aninhados) | `dados #>> '{estoque,quantidade}'` | `20`      |

Exemplo:

```sql
SELECT
  nome,
  dados ->> 'marca' AS marca,
  dados #>> '{estoque,quantidade}' AS quantidade
FROM produtos;
```

---

## ⚙️ 4. **Filtros (WHERE) com JSONB**

Filtrando por valor dentro do JSON:

```sql
SELECT * FROM produtos
WHERE dados ->> 'marca' = 'Dell';
```

Filtrando por campo dentro de objeto aninhado:

```sql
SELECT * FROM produtos
WHERE dados #>> '{estoque,local}' = 'SP';
```

---

## 🚀 5. **Índices para JSONB**

Um dos grandes diferenciais do PostgreSQL é poder indexar campos JSON:

```sql
CREATE INDEX idx_dados_marca ON produtos USING gin (dados jsonb_path_ops);
```

Agora você pode fazer buscas rápidas como:

```sql
SELECT * FROM produtos
WHERE dados @> '{"marca": "Dell"}';
```

(`@>` significa “contém o objeto JSON informado”.)

---

## 🧠 6. **Funções úteis de JSONB**

| Função                   | Descrição                 | Exemplo                                                                  |
| ------------------------ | ------------------------- | ------------------------------------------------------------------------ |
| `jsonb_each()`           | Expande pares chave-valor | `SELECT * FROM jsonb_each('{"a":1,"b":2}')`                              |
| `jsonb_array_elements()` | Expande um array JSON     | `SELECT * FROM jsonb_array_elements('[1,2,3]')`                          |
| `jsonb_build_object()`   | Cria JSON dinâmico        | `SELECT jsonb_build_object('nome', 'PC', 'preco', 5000)`                 |
| `jsonb_set()`            | Atualiza parte do JSON    | `UPDATE produtos SET dados = jsonb_set(dados, '{preco}', '6000', false)` |

---

## 💡 7. **Quando usar JSON**

Use **JSON** quando:

* O formato dos dados é **variável** ou **não totalmente estruturado**.
* Você quer **flexibilidade** (por exemplo, atributos diferentes para cada produto).
* Precisa armazenar configurações, logs ou respostas de APIs.

Evite JSON quando:

* Os dados têm estrutura fixa — prefira **colunas normais**.
* Você precisa de **joins** ou **constraints** entre campos internos do JSON.

---

## Exemplos

## 🧱 **1. Criar a tabela**

Vamos criar uma tabela `produtos` com um campo `dados` do tipo **JSONB**:

```sql
CREATE TABLE produtos (
    id SERIAL PRIMARY KEY,
    nome TEXT NOT NULL,
    dados JSONB
);
```

---

## 📥 **2. Inserir alguns dados**

```sql
INSERT INTO produtos (nome, dados)
VALUES
  ('Notebook', '{"marca": "Dell", "preco": 5500, "estoque": {"quantidade": 20, "local": "SP"}}'),
  ('Mouse', '{"marca": "Logitech", "preco": 150, "estoque": {"quantidade": 200, "local": "RJ"}}'),
  ('Monitor', '{"marca": "Samsung", "preco": 1200, "estoque": {"quantidade": 35, "local": "SP"}}'),
  ('Teclado', '{"marca": "Logitech", "preco": 250}');
```

---

## 🔍 **3. Consultar dados JSON**

### a) Exibir o JSON completo

```sql
SELECT * FROM produtos;
```

### b) Extrair campos específicos

```sql
SELECT
  nome,
  dados ->> 'marca' AS marca,
  dados ->> 'preco' AS preco
FROM produtos;
```

Resultado esperado:

| nome     | marca    | preco |
| -------- | -------- | ----- |
| Notebook | Dell     | 5500  |
| Mouse    | Logitech | 150   |
| Monitor  | Samsung  | 1200  |
| Teclado  | Logitech | 250   |

---

## ⚙️ **4. Acessar campos aninhados**

Para acessar dados dentro de `estoque`:

```sql
SELECT
  nome,
  dados #>> '{estoque,quantidade}' AS quantidade,
  dados #>> '{estoque,local}' AS local
FROM produtos;
```

---

## 🔎 **5. Filtrar por valores dentro do JSON**

### a) Por uma chave simples:

```sql
SELECT * FROM produtos
WHERE dados ->> 'marca' = 'Logitech';
```

### b) Por valor dentro de objeto aninhado:

```sql
SELECT * FROM produtos
WHERE dados #>> '{estoque,local}' = 'SP';
```

### c) Por “contém” (`@>`)

```sql
SELECT * FROM produtos
WHERE dados @> '{"marca": "Dell"}';
```

---

## 🚀 **6. Atualizar partes do JSON**

### a) Alterar um valor:

```sql
UPDATE produtos
SET dados = jsonb_set(dados, '{preco}', '6000', false)
WHERE nome = 'Notebook';
```

### b) Adicionar uma nova chave:

```sql
UPDATE produtos
SET dados = jsonb_set(dados, '{garantia}', '"12 meses"', true)
WHERE nome = 'Monitor';
```
### c) Remover uma chave específica:

```sql
update backup.log SET detalhes = detalhes - 'idade' where id = 6;
```

No PostgreSQL, o quarto argumento da função `jsonb_set` é um valor booleano (`create_missing`) que define **se a chave deve ser criada caso ela não exista** no objeto JSON.

O resumo do comportamento é:
* **`true` (Padrão):** Se a chave já existir, o valor é atualizado. Se a chave **não** existir, ela é criada e o valor é adicionado.
* **`false`:** Se a chave já existir, o valor é atualizado. Se a chave **não** existir, **nada acontece** (o JSON original permanece igual).

---

### 📊 Comparação Direta

| Valor (`create_missing`) | Se a chave EXISTIR | Se a chave NÃO EXISTIR |
| :--- | :--- | :--- |
| **`true`** | Atualiza o valor existente | **Cria a nova chave** com o valor |
| **`false`** | Atualiza o valor existente | **Ignora** (não altera o JSON) |

---

### 🔍 Analisando os seus exemplos:

#### **Exemplo A (`false`):**
```sql
UPDATE produtos SET dados = jsonb_set(dados, '{preco}', '6000', false) WHERE nome = 'Notebook';
```
* **O que faz:** Ele só vai alterar o preço para `6000` se a chave `preco` **já existir** dentro do campo `dados` do Notebook. 
* **Por que usar `false` aqui?** É uma segurança. Se por acaso algum Notebook não tiver a chave `preco` cadastrada, o comando não vai criar essa chave do zero por erro.

#### **Exemplo B (`true`):**
```sql
UPDATE produtos SET dados = jsonb_set(dados, '{garantia}', '"12 meses"', true) WHERE nome = 'Monitor';
```
* **O que faz:** Ele vai procurar a chave `garantia`. Se ela já existir, muda para `"12 meses"`. Se o Monitor nunca teve essa chave antes, ela **será criada** naquele momento.
* **Por que usar `true` aqui?** Porque o objetivo explícito é garantir que o Monitor passe a ter a informação de garantia, mesmo que ela nunca tenha sido inserida antes.


---

## 📊 **7. Criar índice para acelerar buscas**

Se você vai fazer muitas consultas por JSON, crie um índice GIN:

```sql
CREATE INDEX idx_produtos_dados ON produtos USING gin (dados jsonb_path_ops);
```

Agora buscas com `@>` ou `?` ficam bem rápidas!

---

## 🔎 **8. Consultas avançadas**

### a) Buscar produtos que têm uma chave específica:

```sql
SELECT * FROM produtos
WHERE dados ? 'estoque';
```

### b) Buscar produtos com preço maior que 1000:

> Como os valores dentro de JSON são texto, precisamos converter para número:

```sql
SELECT *
FROM produtos
WHERE (dados ->> 'preco')::NUMERIC > 1000;
```

---

## 💡 **Resumo do aprendizado**

| Tópico           | Exemplo                            |
| ---------------- | ---------------------------------- |
| Criar tabela     | `CREATE TABLE ... (dados JSONB)`   |
| Inserir JSON     | `INSERT INTO ... VALUES ('{...}')` |
| Extrair campo    | `dados ->> 'chave'`                |
| Acessar aninhado | `dados #>> '{objeto,chave}'`       |
| Atualizar JSON   | `jsonb_set()`                      |
| Buscar por JSON  | `@>`                               |
| Índice JSONB     | `USING gin`                        |

---
