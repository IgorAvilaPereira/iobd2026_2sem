&nbsp;

**Assuntos - 2º bim.:**

* [UUID](https://github.com/IgorAvilaPereira/iobd2026_1sem/blob/main/01_introducao/dicas/dicas.md#uuid)

* [JSON](https://github.com/IgorAvilaPereira/iobd2026_1sem/blob/main/01_introducao/dicas/dicas.md#json)

* [Herança (Especialização/Generalização) de Tabelas no PostgreSQL](https://github.com/IgorAvilaPereira/iobd2026_1sem/blob/main/01_introducao/dicas/dicas.md#heran%C3%A7a-especializa%C3%A7%C3%A3ogeneraliza%C3%A7%C3%A3o-de-tabelas-no-postgresql)

* [Indices](https://github.com/IgorAvilaPereira/iobd2026_2sem/blob/main/11_heranca_json_postgresql/indices.md)

* DCL

**Videos:**

* [Playlist (Aula 26/05/26) - UUID, Herança de Tabelas e JSON/JSONB](https://www.youtube.com/playlist?list=PLvT8P1q6jMWcGq4eJAhbIuzeTu7dZZNQ0)

* [Playlist - JSON - PostgreSQL (Diversos Videos sobre o Assunto)](https://www.youtube.com/playlist?list=PLvT8P1q6jMWe2XbC16inPLBXd2zIOM5ps)

* [Playlist - Herança de Tabelas - PostgreSQL - PostgreSQL (Diversos Videos sobre o Assunto)](https://www.youtube.com/playlist?list=PLvT8P1q6jMWd5FNYt2N_RwwfE53EQvbdC)

&nbsp;

### Herança de Tabelas PostgreSQL

#### ✅ O que é herdado pelas tabelas filhas (`INHERITS`) no PostgreSQL?

| Tipo de Restrição / Comportamento | É herdado? |
| --------------------------------- | ---------- |
| Colunas                           | ✅ Sim      |
| Tipos de dados                    | ✅ Sim      |
| **PRIMARY KEY**                   | ❌ Não      |
| **UNIQUE**                        | ❌ Não      |
| **CHECK**                         | ❌ Não      |
| **FOREIGN KEY (FK)**              | ❌ Não      |
| **Índices**                       | ❌ Não      |
| **Triggers**                      | ❌ Não      |

---

#### ❗ Ou seja:

* Se você cria uma constraint `UNIQUE(email)` na tabela pai (`pessoa`), **isso não se aplica automaticamente às tabelas filhas**.
* Se você define uma `FOREIGN KEY` na tabela pai, **ela não é aplicada às filhas**.
* Cada tabela filha precisa **definir explicitamente** essas constraints, se quiser garantir o comportamento.

---

#### 📌 Exemplo Prático

##### Tabela Pai:

```sql
CREATE TABLE pessoa (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome TEXT NOT NULL,
    email TEXT NOT NULL,
    CONSTRAINT email_unico UNIQUE (email),
    CHECK (char_length(nome) > 2)
);
```

#### Tabela Filha:

```sql
CREATE TABLE cliente (
    data_cadastro DATE NOT NULL DEFAULT CURRENT_DATE
) INHERITS (pessoa);
```

#### O que acontece aqui?

* A coluna `email` é herdada ✔️
* A constraint `UNIQUE(email)` **não é aplicada** ❌
* A `CHECK (char_length(nome) > 2)` **não é aplicada** ❌
* O `PRIMARY KEY` **não é aplicado** ❌

---

#### 🧠 O que você deve fazer?

Você precisa **reaplicar manualmente as constraints** nas tabelas filhas:

```sql
-- Reaplicando constraints manualmente
ALTER TABLE cliente ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);
ALTER TABLE cliente ADD CONSTRAINT cliente_email_unico UNIQUE (email);
ALTER TABLE cliente ADD CHECK (char_length(nome) > 2);
```

---

#### ✅ Melhor Alternativa para Produção

Evite `INHERITS` se precisar de integridade forte (como `FK`, `UNIQUE`, etc). Em vez disso:

* Crie uma **tabela base** (`pessoa`)
* E especializações com **chave estrangeira** para `pessoa(id)`

#### Exemplo:

```sql
CREATE TABLE pessoa (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL
);

CREATE TABLE cliente (
    pessoa_id UUID PRIMARY KEY REFERENCES pessoa(id),
    data_cadastro DATE NOT NULL
);
```

Essa abordagem suporta 100% de:

* Chaves primárias e estrangeiras ✅
* Checks e constraints ✅
* Índices ✅
* Integridade forte e controle total ✅

&nbsp;

[Baixar todo o material da aula](https://download-directory.github.io/?url=http://github.com/IgorAvilaPereira/iobd2026_2sem/tree/main/./11_heranca_postgresql)
