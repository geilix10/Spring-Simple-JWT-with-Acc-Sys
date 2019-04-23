package at.geilehner.jwt.utils.configuration;

import at.geilehner.jwt.utils.Utils;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class ValidationErrorBuilder {

    public static ValidationError fromBindingErrors(Errors errors) {
        ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");
        for (ObjectError objectError : errors.getAllErrors()) {
            if (!Utils.isNullOrEmpty(objectError.getDefaultMessage())) {
                error.addValidationError(Integer.parseInt(objectError.getDefaultMessage()));
            }
        }
        return error;
    }
}
