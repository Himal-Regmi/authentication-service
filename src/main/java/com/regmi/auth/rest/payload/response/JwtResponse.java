package com.regmi.auth.rest.payload.response;

import java.util.List;

public class JwtResponse {
    private String jwt;
    private Integer id;
    private String username;
    private List<String> roles;

    public JwtResponse(String jwt,Integer id, String username, List<String> roles) {
        this.id=id;
        this.jwt = jwt;
        this.username = username;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
