package br.com.estudo.projetotestapiexternacucumber.integration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Classe Runner para executar o Step no build da aplicação (Maven package).
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features", // Local onde ficam as feature. Exemplo: buscar-produtos.feature.
        tags = "@BuscarProdutos", // A tag da feature.
        glue = {"br.com.estudo.projetotestapiexternacucumber.integration.steps"}, // Local onde estão as classes steps.
        plugin = {"pretty", "html:target/cucumber-reports/buscar-produtos-report.html", "json:target/cucumber-reports/buscar-produtos-report.json"} // Local onde serão gerados os relatórios.
)
public class BuscarProdutosCucumberRunner {
}
