package container;

import com.github.smgueye.app.persistenceadapter.PersistenceAdapterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import web.WebAdapterConfig;

@SpringBootApplication
@Import({
  ApplicationConfiguration.class,
  PersistenceAdapterConfig.class,
  WebAdapterConfig.class,
})
public class CatalogueApplication {
  public static void main(String[] args) {
    SpringApplication.run(CatalogueApplication.class, args);
  }
}
