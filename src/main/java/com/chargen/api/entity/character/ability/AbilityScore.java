package com.chargen.api.entity.character.ability;

import com.chargen.api.entity.BaseEntity;
import com.chargen.api.entity.character.Character;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class AbilityScore extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private EAbility EAbility;

    private int score;

    @OneToMany(
            mappedBy = "abilityScore",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private Set<AbilityScoreModifier> modifiers = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Character character;

    public void addAbilityScoreModifier(AbilityScoreModifier modifier) {
        modifiers.add(modifier);
        modifier.setAbilityScore(this);
    }

    public void removeAbilityScoreModifier(AbilityScoreModifier modifier) {
        modifiers.remove(modifier);
        modifier.setAbilityScore(null);
    }


}
