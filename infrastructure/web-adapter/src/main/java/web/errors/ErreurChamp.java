package web.errors;

import org.jspecify.annotations.Nullable;

public record ErreurChamp(String field, String code, @Nullable String message) {
}
