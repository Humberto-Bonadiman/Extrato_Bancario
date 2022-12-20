package br.com.banco.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(info =
@Info(title = "API Extrato Bancário",
        version = "v1",
        description = "Documentação da API Extrato Bancário"))
public class OpenApiConfiguration {

    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI()
                .components(new Components())
                .info(
                        new io.swagger.v3.oas.models.info.Info()
                                .title("API Extrato Bancário")
                                .version("v1")
                                .license(
                                        new License()
                                                .name("Apache 2.0")
                                                .url("http://springdoc.org")
                                )
                );
    }
}
