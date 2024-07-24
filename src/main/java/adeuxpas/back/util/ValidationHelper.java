package adeuxpas.back.util;

import java.util.*;

import org.springframework.validation.BindingResult;

public final class ValidationHelper {

    private ValidationHelper() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    public static Map<String, String> getErrors(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }
}
