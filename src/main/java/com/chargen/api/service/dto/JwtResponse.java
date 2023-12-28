package com.chargen.api.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {

    private String username;
    private String token;
    private String type = "Bearer";
    private List<String> roles;

    public JwtResponse() {
    }

    public JwtResponse(String username, String token, List<String> roles) {
        this.username = username;
        this.token = token;
        this.roles = roles;
    }
}
