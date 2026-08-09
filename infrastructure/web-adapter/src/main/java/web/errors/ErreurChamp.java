package web.errors;

import org.jspecify.annotations.Nullable;

public record ErreurChamp(String field, @Nullable String defaultMessage) {
}
