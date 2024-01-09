package com.chargen.api.controller.dto;

import com.chargen.api.entity.character.EClass;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CharacterDto {

    private String name;

    private String alignment;

    private String race;

    private List<CharacterClassDto> characterClassDtos = new ArrayList<>();

    private Map<String, Integer> abilities = new HashMap<>();

    @Getter
    @Setter
    public static class CharacterClassDto {

        private EClass name;

        private Integer level;

        private Integer experience;

    }
}
