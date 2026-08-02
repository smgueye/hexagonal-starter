package container;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AdaptorSelector {

  public static final String SPRING_WEB = "web";

  private final Properties adaptateurs;

  public AdaptorSelector(Properties adaptateurs) {
    this.adaptateurs = adaptateurs;
  }

  public static AdaptorSelector fromFile(String cheminVersLeFichier) throws IOException {
    Properties proprietes = new Properties();
    proprietes.load(new FileInputStream(cheminVersLeFichier));
    return new AdaptorSelector(proprietes);
  }

  public String adapterNameForPort(Class<?> typeDuPort) {
    return adaptateurs.getProperty(typeDuPort.getSimpleName());
  }
}
