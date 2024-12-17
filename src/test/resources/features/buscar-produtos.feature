@BuscarProdutos
Feature: Buscar produtos

  @BuscarDadosDeUmProduto
  Scenario: Buscar dados de um produto
    Given que eu realizo uma requisição GET na URL "https://dummyjson.com/products/1"
    Then a resposta deve conter o status 200
    And o campo "title" deve conter "Essence Mascara Lash Princess"
    And o valor do produto deve ser 9.99
