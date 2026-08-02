package persistence;

import application.ports.out.ForObtainingXxx;
import core.lib.Adapter;

@Adapter(name = "test-double")
public class StubXxxProviderAdapter implements ForObtainingXxx {
}
