# springboot-cucumber-test

Exemplo de testes automatizados usando o Cucumber para testar uma API externa com Spring Boot.

Este projeto demonstra como configurar e executar testes de integração automatizados usando Cucumber em uma aplicação
Spring Boot. Os testes são projetados para interagir com uma API externa, realizando operações como autenticação, busca
e adição de produtos.

## Estrutura do Projeto

- `src/main/java`: Contém a classe principal da aplicação Spring Boot
- `src/test/java`: Contém os testes de integração usando Cucumber
  - `integration`: Pacote com os runners do Cucumber e as definições de passos
  - `steps`: Implementações dos passos do Cucumber
- `src/test/resources/features`: Arquivos .feature do Cucumber (não visíveis na estrutura fornecida, mas presumivelmente
  presentes)

## Tecnologias Utilizadas

- Spring Boot
- Cucumber
- JUnit
- Apache HttpClient
- Jackson (para parsing JSON)

## Como Executar os Testes

Para executar os testes, você pode usar o Maven. Na raiz do projeto, execute o seguinte comando:

```
mvn test
```

Isso executará todos os testes Cucumber definidos nos runners:

- AdicionarProdutosCucumberRunner
- BuscarProdutosCucumberRunner
- ObterProdutoAcessoAutenticadoCucumberRunner
- ObterTokenAcessoLoginCucumberRunner

Os relatórios dos testes serão gerados no diretório `target/cucumber-reports`.