package br.com.estudo.projetotestapiexternacucumber.integration.steps.config;

import br.com.estudo.projetotestapiexternacucumber.ProjetoTestApiExternaCucumberApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Classe de configuração do cucumber no spring-boot
 */
@CucumberContextConfiguration
@SpringBootTest(classes = ProjetoTestApiExternaCucumberApplication.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles(profiles = "tests")
public class CucumberSpringConfiguration {
}
