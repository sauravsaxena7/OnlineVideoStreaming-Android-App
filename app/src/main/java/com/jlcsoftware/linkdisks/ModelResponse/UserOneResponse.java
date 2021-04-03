package com.jlcsoftware.linkdisks.ModelResponse;



public class UserOneResponse {

    UserIdResponse _id;

    String email,user_id,pass_code,size;
    boolean admin;

    public UserOneResponse(UserIdResponse _id, String email, String user_id, String pass_code, String size, boolean admin) {
        this._id = _id;
        this.email = email;
        this.user_id = user_id;
        this.pass_code = pass_code;
        this.size = size;
        this.admin = admin;
    }




    public UserIdResponse get_id() {
        return _id;
    }

    public void set_id(UserIdResponse _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPass_code() {
        return pass_code;
    }

    public void setPass_code(String pass_code) {
        this.pass_code = pass_code;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }





}
