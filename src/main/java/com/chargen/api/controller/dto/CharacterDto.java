package com.chargen.api.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CharacterDto {

    private String name;

    List<CharacterClassDto> characterClassDtos = new ArrayList<>();

    @Getter
    @Setter
    public static class CharacterClassDto {

        private String name;

        private Integer level;

        private Integer experience;

    }
}
