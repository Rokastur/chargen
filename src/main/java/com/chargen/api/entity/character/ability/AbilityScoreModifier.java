package com.chargen.api.entity.character.ability;

import com.chargen.api.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AbilityScoreModifier extends BaseEntity {

    private String source;

    private int modifierValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private AbilityScore abilityScore;
}
