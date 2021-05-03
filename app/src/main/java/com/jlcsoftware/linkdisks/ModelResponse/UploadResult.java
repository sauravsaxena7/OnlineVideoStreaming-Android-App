package com.jlcsoftware.linkdisks.ModelResponse;

import java.util.List;

public class UploadResult {


    private String message;
    private String error;


    public UploadResult(String message, String error) {
        this.message = message;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
