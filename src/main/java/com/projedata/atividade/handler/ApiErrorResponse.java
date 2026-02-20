package com.projedata.atividade.handler;

public class ApiErrorResponse {
    private String message;
    private String timestamp;
    private String path;

    public ApiErrorResponse(String message, String path) {
        this.message = message;
        this.timestamp = java.time.LocalDateTime.now().toString();
        this.path = path;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
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
