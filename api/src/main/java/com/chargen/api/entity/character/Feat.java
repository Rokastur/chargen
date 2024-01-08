package com.chargen.api.entity.character;

import com.chargen.api.entity.BaseEntity;
import com.chargen.api.entity.Ruleset;
import com.chargen.api.entity.Source;
import com.chargen.api.entity.character.ability.AbilityScoreModifier;
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
public class Feat extends BaseEntity {

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private Source source;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "feat_ability_score_modifier")
    @JsonManagedReference
    private Set<AbilityScoreModifier> abilityScoreModifiers = new HashSet<>();

    @ManyToMany(mappedBy = "feats")
    @JsonBackReference
    private Set<Character> characters = new HashSet<>();

    @ManyToMany(mappedBy = "allowedFeats")
    @JsonBackReference
    private Set<Ruleset> rulesets = new HashSet<>();

    public void addAbilityScoreModifier(AbilityScoreModifier abilityScoreModifier) {
        abilityScoreModifiers.add(abilityScoreModifier);
        abilityScoreModifier.getFeats().add(this);
    }

    public void removeAbilityScoreModifier(AbilityScoreModifier abilityScoreModifier) {
        abilityScoreModifiers.remove(abilityScoreModifier);
        abilityScoreModifier.getFeats().remove(this);
    }
}
