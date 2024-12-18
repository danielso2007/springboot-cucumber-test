package br.com.estudo.projetotestapiexternacucumber.integration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Classe Runner para executar o Step no build da aplicação (Maven package).
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features", // Local onde ficam as feature. Exemplo: adicionar-produtos.feature.
        tags = "@AdicionarProdutos", // A tag da feature.
        glue = {"br.com.estudo.projetotestapiexternacucumber.integration.steps"}, // Local onde estão as classes steps.
        plugin = {"pretty", "html:target/cucumber-reports/adicionar-produtos-report.html", "json:target/cucumber-reports/adicionar-produtos-report.json"} // Local onde serão gerados os relatórios.
)
public class AdicionarProdutosCucumberRunner {
}
