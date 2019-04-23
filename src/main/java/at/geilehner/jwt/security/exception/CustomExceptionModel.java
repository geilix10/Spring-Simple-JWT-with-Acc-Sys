package at.geilehner.jwt.security.exception;

import org.springframework.http.HttpStatus;

public class CustomExceptionModel {
    private String cause;
    private String message;
    private HttpStatus httpStatus;
    private String localizedMessage;

    public CustomExceptionModel(String cause, String message, HttpStatus httpStatus, String localizedMessage) {
        this.cause = cause;
        this.message = message;
        this.httpStatus = httpStatus;
        this.localizedMessage = localizedMessage;
    }

    public CustomExceptionModel(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public CustomExceptionModel() {
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getLocalizedMessage() {
        return localizedMessage;
    }

    public void setLocalizedMessage(String localizedMessage) {
        this.localizedMessage = localizedMessage;
    }
}
