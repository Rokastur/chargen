package com.chargen.api.controller.dto;

import com.chargen.api.entity.character.EClass;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CharacterDto {

    private String name;

    private String alignment;

    private String race;

    List<CharacterClassDto> characterClassDtos = new ArrayList<>();

    @Getter
    @Setter
    public static class CharacterClassDto {

        private EClass name;

        private Integer level;

        private Integer experience;

    }
}
