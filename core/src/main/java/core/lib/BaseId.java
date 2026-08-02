package core.lib;

import java.util.Objects;

public abstract class BaseId<T> extends ValueObject {

  private final T value;

  public BaseId(T value) {
    this.value = value;
  }

  public T value() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    BaseId<?> baseId = (BaseId<?>) o;
    return Objects.equals(value, baseId.value);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(value);
  }
}
