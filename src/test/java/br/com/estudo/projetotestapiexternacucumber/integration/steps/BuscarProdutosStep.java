package br.com.estudo.projetotestapiexternacucumber.integration.steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BuscarProdutosStep {

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
    private void obterRetornoUrlExterna(String url) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet(url);
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

    /**
     * Passando id na URL.
     *
     * @param url A URL para a requisição.
     * @param id  O Id do produto.
     */
    private void obterRetornoUrlExterna(String url, String id) {
        obterRetornoUrlExterna(String.format("%s/%s", url, id));
    }

    // Dado
    @Given("que eu realizo uma requisição GET na URL {string}")
    public void queEuRealizoUmaRequisicaoGETNaURL(String url) {
        // Chamando a URL e preenchendo as responses.
        obterRetornoUrlExterna(url);
    }

    // Então
    @Then("a resposta deve conter o status {int}")
    public void aRespostaDeveConterOStatus(int statusCode) {
        assertEquals(statusCode, response.getStatusLine().getStatusCode());
    }

    @And("o retorno do campo {string} deve ser uma lista de produtos")
    public void oRetornoDoCampoDeveSerUmaListaDeProdutos(String campo) {
        // Obter a lista de products, conforme "https://dummyjson.com/products"
        JsonNode listaProdutosJsonNode = jsonResponse.get(campo);

        // Criado a lista, preencher com o ID, apenas para verificarmos se o retorno trouxe dados.
        List<Integer> produtos = new ArrayList<>();
        for (JsonNode node : listaProdutosJsonNode) {
            produtos.add(node.get("id").asInt());
        }

        // A lista de produtos não pode ser vazia.
        assertFalse(produtos.isEmpty());
    }

    // Dados
    @Given("que eu realizo uma requisição GET na URL {string} passando id {string}")
    public void queEuRealizoUmaRequisicaoGETNaURLPassandoId(String url, String id) {
        obterRetornoUrlExterna(url, id);
    }

    @And("o campo {string} deve conter {string}")
    public void oCampoDeveConter(String campo, String texto) {
        ///  Obtendo o campo "title", do json, em texto.
        String textoCampoTitle = jsonResponse.get(campo).asText();
        assertEquals(textoCampoTitle, texto);
    }

    @And("o valor do campo {string} do produto deve ser {double}")
    public void oValorDoCampoDoProdutoDeveSer(String campo, double price) {
        assertEquals(jsonResponse.get(campo).asDouble(), price, 0.0);
    }

    @Given("que eu realizo uma requisição GET na URL {string} passando um id {string} não existente")
    public void queEuRealizoUmaRequisicaoGETNaURLPassandoUmIdNaoExistente(String url, String id) {
        obterRetornoUrlExterna(url, id);
    }

    @And("no retorno, o campo {string} deve conter {string}")
    public void noRetornoOCampoDeveConter(String campo, String texto) {
    }
}