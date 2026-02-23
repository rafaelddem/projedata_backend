package com.projedata.atividade.handler;

import java.util.List;

public class ApiErrorResponse {
    private String timestamp;
    private String path;
    private List<String> errors;

    public ApiErrorResponse(List<String> errors, String path) {
        this.errors = errors;
        this.timestamp = java.time.LocalDateTime.now().toString();
        this.path = path;
    }

    public List<String> getErrors() {
        return this.errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
