package com.chargen.api.entity;

import com.chargen.api.entity.character.Background;
import com.chargen.api.entity.character.CharacterClass;
import com.chargen.api.entity.character.Feat;
import com.chargen.api.entity.character.Race;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Defines rules for the campaign.
 */
@Getter
@Setter
@Entity
public class Ruleset extends BaseEntity {

    private String name;

    @OneToMany(
            mappedBy = "ruleset",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Campaign> campaigns = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "ruleset_feats",
            joinColumns = @JoinColumn(name = "ruleset_id"),
            inverseJoinColumns = @JoinColumn(name = "feat_id")
    )
    private Set<Feat> allowedFeats = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "ruleset_races",
            joinColumns = @JoinColumn(name = "ruleset_id"),
            inverseJoinColumns = @JoinColumn(name = "race_id")
    )
    private Set<Race> allowedRaces = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "ruleset_classes",
            joinColumns = @JoinColumn(name = "ruleset_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id")
    )
    private Set<CharacterClass> allowedClasses = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "ruleset_feats",
            joinColumns = @JoinColumn(name = "ruleset_id"),
            inverseJoinColumns = @JoinColumn(name = "background_id")
    )
    private Set<Background> allowedBackgrounds = new HashSet<>();


}
