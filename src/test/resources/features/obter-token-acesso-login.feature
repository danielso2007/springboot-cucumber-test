@ObterTokenAcessoLogin
Feature: Obter token de acesso

###
## Cenários de sucesso
###
  @ObterTokenDeAcessoComSucesso
  Scenario: Obter token de acesso com sucesso
    Given que eu realizo uma requisição POST, para obter token válido, na URL "https://dummyjson.com/auth/login"
    Then a resposta, para obter o token, deve conter o status 200
    And no retorno do objeto, o campo "email" deve conter o texto "emily.johnson@x.dummyjson.com"
    And o campos campo "accessToken" e "refreshToken" diferentes de nulo

###
## Cenários de falha
###
  @ObterTokenDeAcessoComCredenciaisInvalidas
  Scenario: Obter token de acesso com credenciais inválidas
    Given que eu realizo uma requisição POST para obter token na URL "https://dummyjson.com/auth/login"
    Then a resposta, para obter o token, deve conter o status 400
    And no retorno do objeto, o campo "message" com a mensagem de erro "Invalid credentials"