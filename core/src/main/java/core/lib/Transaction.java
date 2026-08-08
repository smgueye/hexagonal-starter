package core.lib;

import java.util.function.Supplier;

@FunctionalInterface
public interface Transaction {

  <T> T executer(Supplier<T> handler) throws Exception;
}

