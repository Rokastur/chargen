package com.chargen.api.entity.character;

import com.chargen.api.entity.BaseEntity;
import com.chargen.api.entity.character.ability.AbilityScoreModifier;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Race extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    private Character character;

    private String race;

    @OneToMany(
            mappedBy = "race",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private Set<AbilityScoreModifier> modifiers = new HashSet<>();

}
