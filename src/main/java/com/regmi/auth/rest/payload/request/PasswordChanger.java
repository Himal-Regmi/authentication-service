package com.regmi.auth.rest.payload.request;

public class PasswordChanger {
    private String currentPass;
    private String newPass;

    public PasswordChanger(String oldPass, String newPass) {
        this.currentPass = oldPass;
        this.newPass = newPass;
    }

    public PasswordChanger(){

    }

    public String getCurrentPass() {
        return currentPass;
    }

    public void setCurrentPass(String currentPass) {
        this.currentPass = currentPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }
}
