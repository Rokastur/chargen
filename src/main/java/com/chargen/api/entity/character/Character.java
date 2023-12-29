package com.chargen.api.entity.character;

import com.chargen.api.entity.Account;
import com.chargen.api.entity.BaseEntity;
import com.chargen.api.entity.character.ability.AbilityScore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Character extends BaseEntity {

    private String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CharacterClass> characterClass;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @OneToMany(
            mappedBy = "character",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private Set<AbilityScore> abilityScores = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private EAlignment alignment;

    public void addAbilityScore(AbilityScore abilityScore) {
        abilityScores.add(abilityScore);
        abilityScore.setCharacter(this);
    }

    public void removeAbilityScore(AbilityScore abilityScore) {
        abilityScores.remove(abilityScore);
        abilityScore.setCharacter(null);
    }

}
