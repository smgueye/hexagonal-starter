package web.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@OpenAPIDefinition(
  info = @Info(
    title = "Catalogue API",
    version = "0.0.1",
    description = "Api de gestion du catalogue de produits"
  )
)
public class OpenApiConfiguration {
}
