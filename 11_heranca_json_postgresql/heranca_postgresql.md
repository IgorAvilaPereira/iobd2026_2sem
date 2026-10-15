## 📚 Teoria da Herança no PostgreSQL## O Conceito Objeto-Relacional

O PostgreSQL não é apenas um banco relacional tradicional (SQL). Ele é objeto-relacional. Isso significa que ele implementa conceitos de Orientação a Objetos diretamente no banco de dados, como a herança e a polimorfismo.
## Como Funciona por Baixo dos Panos?

* Relação de Ancestralidade: A tabela filha aponta para a tabela mãe.
* Tabelas Físicas Distintas: Cada tabela criada (mãe ou filha) possui seu próprio arquivo físico de armazenamento em disco.
* Direcionamento de Consultas: Quando você faz um SELECT na tabela mãe, o PostgreSQL faz uma busca sequencial ou de índice na tabela mãe e, em seguida, varre as tabelas filhas (via comando APPEND), unificando os resultados de forma transparente.

## Benefícios Práticos

   1. Reutilização de Código: Você define colunas padronizadas (como campos de auditoria: criado_em, atualizado_em) apenas uma vez na tabela base.
   2. Consultas Polimórficas: É possível buscar dados de múltiplos tipos de entidades com uma única consulta na tabela mãe.

------------------------------
## 💻 Exemplo Prático Completo (Markdown / SQL)
Abaixo está o script SQL completo estruturado para simular um sistema de e-commerce com produtos físicos e digitais.

```sql
-- ==========================================-- 1. CRIAÇÃO DA TABELA MÃE (BASE)-- ==========================================
-- Contém os atributos que todo produto possui.
CREATE TABLE produtos (
    id SERIAL,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- ==========================================-- 2. CRIAÇÃO DAS TABELAS FILHAS-- ==========================================
-- Tabela Filha 1: Produtos Físicos
-- Herda: id, nome, preco, data_cadastro-- Adiciona: peso, dimensoes

CREATE TABLE produtos_fisicos (
    peso FLOAT NOT NULL,
    dimensoes VARCHAR(50)
) INHERITS (produtos);


-- Tabela Filha 2: Produtos Digitais
-- Herda: id, nome, preco, data_cadastro-- Adiciona: tamanho_arquivo, link_download

CREATE TABLE produtos_digitais (
    tamanho_arquivo_mb INT NOT NULL,
    link_download TEXT NOT NULL
) INHERITS (produtos);
```

## Inserção de Dados (Manipulação)
O comportamento de inserção é estrito: os dados inseridos na tabela filha não residem fisicamente na tabela mãe.

```sql
-- Inserindo um produto físico
INSERT INTO produtos_fisicos (nome, preco, peso, dimensoes)VALUES ('Cadeira Gamer', 1200.00, 18.5, '120x60x60cm');
-- Inserindo um produto digital
INSERT INTO produtos_digitais (nome, preco, tamanho_arquivo_mb, link_download) VALUES ('Curso de PostgreSQL PDF', 49.90, 25, 'https://site.com');
-- Inserindo um produto genérico diretamente na tabela mãe
INSERT INTO produtos (nome, preco)VALUES ('Serviço de Consultoria Hora', 250.00);
```

## Consultas Avançadas (Seleção)
Aqui vemos o comportamento polimórfico do PostgreSQL na prática.

```sql
SELECT id, nome, preco, data_cadastro FROM produtos;
```

2. Consulta Exclusiva (Isolando a tabela Mãe)Para ignorar os dados das tabelas filhas e trazer apenas o que foi inserido diretamente na tabela base, utilize a palavra-chave ONLY.

```sql
SELECT id, nome, preco FROM ONLY produtos;
```

3. Identificando a Origem do Registro (tableoid)

```sql
SELECT tableoid::regclass AS tabela_origem, nome, preco FROM produtos;
```

## ⚠️ Limitações Cruciais da Herança
Embora pareça uma solução perfeita, a herança no PostgreSQL possui restrições severas que fazem com que muitos desenvolvedores a evitem em sistemas transacionais puros:

   1. Chaves Primárias Não Globais: Uma restrição de PRIMARY KEY ou UNIQUE na tabela mãe não se aplica às tabelas filhas. Se você definir id SERIAL PRIMARY KEY na tabela produtos, o Postgres garante a unicidade do ID dentro da tabela mãe, mas você pode ter um ID 1 na tabela produtos_fisicos e outro ID 1 na tabela produtos_digitais.
   2. Chaves Estrangeiras Unidirecionais: Se outra tabela do banco (ex: itens_pedido) apontar para produtos(id), essa chave estrangeira não conseguirá validar ou enxergar os IDs que estão gravados nas tabelas filhas.
   3. Índices Não Herdados: Criar um índice na tabela mãe não cria o índice automaticamente nas tabelas filhas. Você precisa criar os índices manualmente em cada filha.

## Quando usar?

* Histórico e Auditoria: Guardar logs antigos em tabelas filhas divididas por ano/mês.
* Organização Lógica: Quando você precisa apenas ler os dados consolidados e não depende fortemente de integridade referencial (FOREIGN KEY) entre tabelas filhas.
