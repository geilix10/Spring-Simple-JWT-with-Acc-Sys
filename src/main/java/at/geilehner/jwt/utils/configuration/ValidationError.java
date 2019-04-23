package at.geilehner.jwt.utils.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ValidationError {
    private final String message;
    private final boolean success = false;
    private final int status = 400;
    private final String error = "Bad Request";
    private final Date timestamp;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Integer> errors = new ArrayList<>();

    public ValidationError(String errorMessage) {
        this.message = errorMessage;
        timestamp = new Date();
    }

    public void addValidationError(Integer error) {
        errors.add(error);
    }

    public List<Integer> getErrors() {
        return errors;
    }


    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
