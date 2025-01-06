package br.com.estudo.projetotestapiexternacucumber.integration.steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class ObterProdutoAcessoAutenticadoStep {

    /**
     * Objeto para guardar o retorno da requisição.
     */
    private HttpResponse response;
    /**
     * Objeto para transformar o body do retorno em objeto.
     */
    private JsonNode jsonResponse;

    /**
     * Método usado para realizar as chamadas para a API.
     *
     * @param url A URL que será chamada.
     */
    private void obterRetornoUrlExterna(String url, String token) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            request.setHeader("Authorization", "Bearer " + token);
            try (CloseableHttpResponse response = client.execute(request);
                 InputStream inputStream = response.getEntity().getContent()) {

                // Transformar o Json da resposta em objeto.
                ObjectMapper objectMapper = new ObjectMapper();
                jsonResponse = objectMapper.readTree(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Obter o token passando as credenciais.
     * @param username O login.
     * @param password A senha.
     * @return Retorna o token.
     */
    private String obterToken(String username, String password) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost("https://dummyjson.com/auth/login");

            final String json = String.format("{\"username\":\"%s\", \"password\":\"%s\", \"expiresInMins\":60}", username, password);
            final StringEntity entity = new StringEntity(json);

            request.setEntity(entity);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");

            HttpResponse responseToken = client.execute(request);

            // Transformar o Json da resposta em objeto.
            try (InputStream inputStream = responseToken.getEntity().getContent()) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonResponseToken = objectMapper.readTree(inputStream);

                return jsonResponseToken.get("accessToken").asText();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Given("que eu realizo uma requisição GET com envio do token na URL {string}")
    public void queEuRealizoUmaRequisicaoGETComEnvioDoTokenNaURL(String url) {
        String token = obterToken("emilys", "emilyspass");
        obterRetornoUrlExterna(url, token);
    }

    @Then("o produto deve ser retornado e o campo {string} deve conter {string}")
    public void oProdutoDeveSerRetornadoEOCampoDeveConter(String campo, String texto) {
        assertEquals(texto, jsonResponse.get(campo).asText());
    }

    @Given("que eu realizo uma requisição GET, com envio do token errado, na URL {string}")
    public void queEuRealizoUmaRequisicaoGETComEnvioDoTokenErradoNaURL(String url) {
        obterRetornoUrlExterna(url, "kjashdlkasjhd87777787asd");
    }

    @Then("no retorno do objeto, o campo {string} com a mensagem de erro de token inválido {string}")
    public void noRetornoDoObjetoOCampoComAMensagemDeErroDeTokenInvalido(String campo, String texto) {
        assertEquals(texto, jsonResponse.get(campo).asText());
    }
}
