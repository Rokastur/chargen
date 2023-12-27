package com.chargen.api.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {

    private Long id;
    private String username;
    private String token;
    private String type = "Bearer";
    private List<String> roles;

    public JwtResponse() {
    }

    public JwtResponse(Long id, String username, String token, List<String> roles) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.roles = roles;
    }
}
