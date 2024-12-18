@AdicionarProdutos
Feature: Adicionar produtos

###
## Cenários de sucesso
###
  @AdicionarUmNovoProdutos
  Scenario: Adicionar um novo produto
    Given que eu realizo uma requisição POST na URL "https://dummyjson.com/products/add"
    Then a resposta, ao adicionar produto, deve conter o status 201
    And o retorno do json deve ter o campo "id" diferente de nulo