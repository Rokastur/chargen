package com.chargen.api.entity.character;

import com.chargen.api.entity.BaseEntity;
import com.chargen.api.entity.character.ability.Ability;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class CharacterClass extends BaseEntity {

    protected CharacterClass() {
    }

    public CharacterClass(EClass name) {
        this.name = name;
        hitDice = initializeHitDice(name);
    }

    @Enumerated(EnumType.STRING)
    private EClass name;

    private Integer level;

    private Integer experiencePoints;

    private Integer hitDice;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "primary_abilities",
            joinColumns = @JoinColumn(name = "character_class_id"),
            inverseJoinColumns = @JoinColumn(name = "ability_id"))
    private Set<Ability> abilities = new HashSet<>();

    private Integer initializeHitDice(EClass name) {
        return switch (name) {
            case BARBARIAN -> 12;
            case FIGHTER, PALADIN, RANGER -> 10;
            case BARD, CLERIC, DRUID, MONK, ROGUE, WARLOCK -> 8;
            case SORCERER, WIZARD -> 6;
        };
    }


}
