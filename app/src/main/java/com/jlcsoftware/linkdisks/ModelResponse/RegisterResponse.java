package com.jlcsoftware.linkdisks.ModelResponse;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {
    @SerializedName("error")
    String error;
    String message;
    String token;

    public RegisterResponse(String error, String message, String token) {
        this.error = error;
        this.message = message;
        this.token = token;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}


//for performaction on api passcode:Xyab1908vfuhfA0
