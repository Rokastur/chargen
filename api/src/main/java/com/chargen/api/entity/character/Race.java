package com.chargen.api.entity.character;

import com.chargen.api.entity.BaseEntity;
import com.chargen.api.entity.Ruleset;
import com.chargen.api.entity.character.ability.AbilityScoreModifier;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Race extends BaseEntity {

    @OneToMany(mappedBy = "race")
    @JsonBackReference
    private Set<Character> characters;

    private String name;

    @OneToMany(
            mappedBy = "race",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonBackReference
    private Set<AbilityScoreModifier> modifiers = new HashSet<>();

    @ManyToMany(mappedBy = "allowedRaces")
    @JsonBackReference
    private Set<Ruleset> ruleset = new HashSet<>();

    public void addCharacter(Character character) {
        characters.add(character);
        character.setRace(this);
    }

    public void removeCharacter(Character character) {
        characters.remove(character);
        character.setRace(null);
    }

}
