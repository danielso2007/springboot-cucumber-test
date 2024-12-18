package br.com.estudo.projetotestapiexternacucumber.integration.steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ObterTokenAcessoLoginStep {

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
     * @param url    A URL que será chamada.
     * @param entity O body da requisição.
     */
    private void enviarRequisicaoPost(String url, StringEntity entity) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost request = new HttpPost(url);

            request.setEntity(entity);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");

            response = client.execute(request);

            // Transformar o Json da resposta em objeto.
            InputStream inputStream = response.getEntity().getContent();
            ObjectMapper objectMapper = new ObjectMapper();
            jsonResponse = objectMapper.readTree(inputStream);

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Given("que eu realizo uma requisição POST, para obter token válido, na URL {string}")
    public void queEuRealizoUmaRequisicaoPOSTParaObterTokenValidoNaURL(String url) throws UnsupportedEncodingException {
        final String json = "{\"username\":\"emilys\", \"password\":\"emilyspass\", \"expiresInMins\":60}";
        final StringEntity entity = new StringEntity(json);
        enviarRequisicaoPost(url, entity);
    }

    @Then("a resposta, para obter o token, deve conter o status {int}")
    public void aRespostaParaObterOTokenDeveConterOStatus(int statusCode) {
        assertEquals(statusCode, response.getStatusLine().getStatusCode());
    }

    @And("no retorno do objeto, o campo {string} deve conter o texto {string}")
    public void noRetornoDoObjetoOCampoDeveConterOTexto(String campo, String texto) {
        assertEquals(texto, jsonResponse.get(campo).asText());
    }

    @And("o campos campo {string} e {string} diferentes de nulo")
    public void oCamposCampoEDiferentesDeNulo(String campo1, String campo2) {
        assertNotNull(campo1);
        assertNotNull(campo2);
    }

    @Given("que eu realizo uma requisição POST para obter token na URL {string}")
    public void queEuRealizoUmaRequisicaoPOSTParaObterTokenNaURL(String url) throws UnsupportedEncodingException {
        final String json = "{\"username\":\"XXXXXXX\", \"password\":\"YYYYYYY\", \"expiresInMins\":60}";
        final StringEntity entity = new StringEntity(json);
        enviarRequisicaoPost(url, entity);
    }

    @And("no retorno do objeto, o campo {string} com a mensagem de erro {string}")
    public void noRetornoDoObjetoOCampoComAMensagemDeErro(String campo, String texto) {
        assertEquals(texto, jsonResponse.get(campo).asText());
    }
}
