@BuscarProdutos
Feature: Buscar produtos

###
## Cenários de sucesso
###
  @BuscarProdutos
  Scenario: Buscar todos os produtos
    Given que eu realizo uma requisição GET na URL "https://dummyjson.com/products"
    Then a resposta deve conter o status 200
    And o retorno do campo "products" deve ser uma lista de produtos

  @BuscarDadosDeUmProduto
  Scenario: Buscar dados de um produto
    Given que eu realizo uma requisição GET na URL "https://dummyjson.com/products" passando id "1"
    Then a resposta deve conter o status 200
    And o campo "title" deve conter "Essence Mascara Lash Princess"
    And o valor do campo "price" do produto deve ser 9.99

###
## Cenários de falha
###
  @BuscarDadosDeUmProdutoNaoExistente
  Scenario: Buscar dados de um produto não existente
    Given que eu realizo uma requisição GET na URL "https://dummyjson.com/products" passando um id "9999" não existente
    Then a resposta deve conter o status 404
    And no retorno, o campo "message" deve conter "Product with id '9999' not found"