# 1. Ajuste a estrutura de pastas

Coloque seus arquivos HTML diretamente na raiz da pasta public:

```
src/
└── main/
    └── resources/
        └── public/
            ├── index.html
            ├── sobre.html
            └── css/
                └── style.css
```

## 2. Mantenha o Javalin apontado para a pasta pública
O código Java permanece simples, apenas inicializando o servidor e apontando para a pasta public:

```java
import io.javalin.Javalin;import io.javalin.http.staticfiles.Location;
public class App {
    public static void main(String[] args) {
        Javalin.create(config -> {
            // Isso faz o Javalin servir index.html, sobre.html, CSS, imagens, etc.
            config.staticFiles.add("/public", Location.CLASSPATH);
        }).start(7070);
    }
}
```
## 3. Como vincular o link no seu arquivo index.html
Dentro do seu arquivo HTML puro, a tag <link> deve usar a barra / no início para garantir que o caminho seja absoluto em relação à raiz do servidor (localhost:7070/):

```html
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Minha Página HTML Puro</title>
    <!-- O caminho começa com '/' para funcionar em qualquer subdiretório -->
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <h1>Página carregada com sucesso!</h1>
</body>
</html>
```
