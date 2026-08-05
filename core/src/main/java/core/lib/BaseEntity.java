package core.lib;

import java.util.Objects;

public abstract class BaseEntity<ID extends ValueObject> extends ValidationAssertive{

  private ID id;

  protected BaseEntity(ID id) {
    verifieArgumentNonNull(id, "L'identifiant est requis.");
    this.id = id;
  }

  public final ID id() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    BaseEntity<?> that = (BaseEntity<?>) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
