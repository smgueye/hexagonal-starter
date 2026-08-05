package core.lib;

public abstract class AggregateRoot<ID extends ValueObject> extends BaseEntity<ID> {

  protected AggregateRoot(ID id) {
    super(id);
  }
}
