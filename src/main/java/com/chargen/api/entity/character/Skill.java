package com.chargen.api.entity.character;

import com.chargen.api.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Skill extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ESkill skill;

    private boolean proficient;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Character character;

}
