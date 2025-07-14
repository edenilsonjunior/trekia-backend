package br.edu.ifsp.arq.trekia.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        var securityScheme = new SecurityScheme()
                .name("Authorization")
                .type(Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(In.HEADER);

        return new OpenAPI()
            .info(new Info()
                .title("Trekia API")
                .version("1.0")
                .description("Sistema para gerenciamento de viagens")
                .contact(new Contact()
                    .name("Código fonte da aplicação")
                    .url("https://github.com/edenilsonjunior/trekia-backend")
                )
            )
            .addSecurityItem(new SecurityRequirement().addList("Authorization"))
            .components(new Components().addSecuritySchemes("Authorization", securityScheme)
        );
    }
}