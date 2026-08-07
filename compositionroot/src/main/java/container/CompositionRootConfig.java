package container;

import com.github.smgueye.app.persistenceadapter.PersistenceAdapterConfig;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@EnableAutoConfiguration
@Import(PersistenceAdapterConfig.class)
public class CompositionRootConfig {
}
