package com.chargen.api.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllowedAbilityDto {

    public AllowedAbilityDto(String name) {
        this.name = name;
    }

    private String name;
}
