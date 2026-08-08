package core.lib;

public interface AdapterProvider<T> {

  Class<T> port();

  String name();

  T create();
}
