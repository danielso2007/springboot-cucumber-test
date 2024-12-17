package br.com.estudo.projetotestapiexternacucumber.integration.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class BuscarProdutosStep {

    @Given("que eu realizo uma requisição GET na URL {string}")
    public void queEuRealizoUmaRequisicaoGETNaURL(String arg0) {
    }

    @Then("a resposta deve conter o status {int}")
    public void aRespostaDeveConterOStatus(int arg0) {
    }

    @And("o campo {string} deve conter {string}")
    public void oCampoDeveConter(String arg0, String arg1) {
    }

    @And("o valor do produto deve ser {double}")
    public void oValorDoProdutoDeveSer(double arg1) {
    }
}
