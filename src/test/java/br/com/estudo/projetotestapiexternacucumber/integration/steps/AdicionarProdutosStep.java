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

public class AdicionarProdutosStep {

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

    @Given("que eu realizo uma requisição POST na URL {string}")
    public void queEuRealizoUmaRequisicaoPOSTNaURL(String url) throws UnsupportedEncodingException {
        final String json = "{\"title\":\"Essence Mascara Lash Princess\"," +
                "\"description\":\"Mascara Lash Princess is a popular mascara known for its volumizing\"," +
                "\"category\":\"beauty\"," +
                "\"price\":100.87," +
                "\"discountPercentage\":8.88," +
                "\"rating\":4.99," +
                "\"stock\":10}";
        final StringEntity entity = new StringEntity(json);
        enviarRequisicaoPost(url, entity);
    }

    @Then("a resposta, ao adicionar produto, deve conter o status {int}")
    public void aRespostaAoAdicionarProdutoDeveConterOStatus(int statusCode) {
        assertEquals(statusCode, response.getStatusLine().getStatusCode());
    }

    @And("o retorno do json deve ter o campo {string} diferente de nulo")
    public void oRetornoDoJsonDeveTerOCampoDiferenteDeNulo(String campo) {
        // Sendo o id um campo não nulo, garante que o produto foi cadastrado.
        assertNotNull(jsonResponse.get(campo));
    }

}
