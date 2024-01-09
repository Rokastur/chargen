package com.chargen.api.entity.character.ability;

import com.chargen.api.entity.BaseEntity;
import com.chargen.api.entity.Ruleset;
import com.chargen.api.entity.character.CharacterClass;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Ability extends BaseEntity {

    private String name;

    @ManyToMany(mappedBy = "allowedAbilities")
    @JsonBackReference
    private Set<Ruleset> ruleset = new HashSet<>();

    @ManyToMany(mappedBy = "abilities")
    private Set<CharacterClass> characterClasses = new HashSet<>();
}
