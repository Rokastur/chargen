package com.chargen.api.entity.character.ability;

import com.chargen.api.entity.BaseEntity;
import com.chargen.api.entity.character.Feat;
import com.chargen.api.entity.character.Race;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class AbilityScoreModifier extends BaseEntity {

    private String source;

    private int modifierValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private AbilityScore abilityScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    private Race race;

    @ManyToMany(mappedBy = "abilityScoreModifiers")
    @JsonBackReference
    private Set<Feat> feats = new HashSet<>();
}
