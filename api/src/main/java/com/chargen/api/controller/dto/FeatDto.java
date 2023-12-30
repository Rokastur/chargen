package com.chargen.api.controller.dto;

import com.chargen.api.entity.Source;
import com.chargen.api.entity.character.ability.EAbility;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeatDto {

    private String name;
    private String description;
    private Source source;
    private EAbility ability;
    private int abilityIncrease;

}
