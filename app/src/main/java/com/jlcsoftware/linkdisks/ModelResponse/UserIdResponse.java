package com.jlcsoftware.linkdisks.ModelResponse;

import com.google.gson.annotations.SerializedName;

public class UserIdResponse {

    @SerializedName("$oid")

    String $oid;

    public UserIdResponse(Object $oid) {
        this.$oid = (String) $oid;
    }

    public Object get$oid() {
        return $oid;
    }

    public void set$oid(Object $oid) {
        this.$oid = (String) $oid;
    }
}
