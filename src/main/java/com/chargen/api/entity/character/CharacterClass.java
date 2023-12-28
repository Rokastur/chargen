package com.chargen.api.entity.character;

import com.chargen.api.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CharacterClass extends BaseEntity {

    private String name;

    private Integer level;

    private Integer experiencePoints;

}
