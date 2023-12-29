package com.chargen.api.entity.character;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class CharacterDescription {

    private Integer age;

    private Double height;

    private Double weight;

    private String eyes;

    private String skin;

    private String hair;

    private String appearance;

    private String backstory;
}
