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

    private boolean inspiration;

    private int proficiencyBonus;

    @Embedded
    private CharacterDescription description;

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

    @OneToMany(
            mappedBy = "character",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private Set<Skill> skills = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "character_languages",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private Set<Language> languages = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Background background;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "character_feats",
        joinColumns = @JoinColumn(name = "character_id"),
        inverseJoinColumns = @JoinColumn(name = "feat_id"))
    private Set<Feat> feats = new HashSet<>();


    public void addAbilityScore(AbilityScore abilityScore) {
        abilityScores.add(abilityScore);
        abilityScore.setCharacter(this);
    }

    public void removeAbilityScore(AbilityScore abilityScore) {
        abilityScores.remove(abilityScore);
        abilityScore.setCharacter(null);
    }

}
