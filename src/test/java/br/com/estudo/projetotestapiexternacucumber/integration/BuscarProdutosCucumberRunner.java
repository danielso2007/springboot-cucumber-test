package br.com.estudo.projetotestapiexternacucumber.integration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        tags = "@BuscarProdutos",
        glue = {"br.com.estudo.projetotestapiexternacucumber.integration.steps"},
        plugin = {"pretty", "html:target/cucumber-reports/buscar-produtos-report.html", "json:target/cucumber-reports/buscar-produtos-report.json"}
)
public class BuscarProdutosCucumberRunner {
}
