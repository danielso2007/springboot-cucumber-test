@ObterProdutoAcessoAutenticado
Feature: Obter produto acesso autenticado

###
## Cenários de sucesso
###
  @ObterProdutoPorIdComAcessoAutenticado
  Scenario: Obter produto por id com acesso autenticado
    Given que eu realizo uma requisição GET com envio do token na URL "https://dummyjson.com/auth/products/2"
    Then o produto deve ser retornado e o campo "title" deve conter "Eyeshadow Palette with Mirror"

###
## Cenários de falha
###
  @ObterProdutoPorIdComCredenciaisErradas
  Scenario: Obter produto por id com credenciais erradas
    Given que eu realizo uma requisição GET, com envio do token errado, na URL "https://dummyjson.com/auth/products/2"
    Then no retorno do objeto, o campo "message" com a mensagem de erro de token inválido "Invalid/Expired Token!"