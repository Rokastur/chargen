package com.chargen.api.entity;

import com.chargen.api.entity.character.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private Set<Campaign> campaigns = new HashSet<>();

    public void addCampaign(Campaign campaign) {
        campaigns.add(campaign);
        campaign.setRuleset(this);
    }

    public void removeCampaign(Campaign campaign) {
        campaigns.remove(campaign);
        campaign.setRuleset(null);
    }

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "ruleset_feats",
            joinColumns = @JoinColumn(name = "ruleset_id"),
            inverseJoinColumns = @JoinColumn(name = "feat_id")
    )
    @JsonManagedReference
    private Set<Feat> allowedFeats = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "ruleset_races",
            joinColumns = @JoinColumn(name = "ruleset_id"),
            inverseJoinColumns = @JoinColumn(name = "race_id")
    )
    @JsonManagedReference
    private Set<Race> allowedRaces = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "ruleset_classes",
            joinColumns = @JoinColumn(name = "ruleset_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id")
    )
    @JsonManagedReference
    private Set<CharacterClass> allowedClasses = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "ruleset_feats",
            joinColumns = @JoinColumn(name = "ruleset_id"),
            inverseJoinColumns = @JoinColumn(name = "background_id")
    )
    @JsonManagedReference
    private Set<Background> allowedBackgrounds = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "ruleset_alignments",
            joinColumns = @JoinColumn(name = "ruleset_id"),
            inverseJoinColumns = @JoinColumn(name = "alignment_id")
    )
    @JsonManagedReference
    private Set<Alignment> allowedAlignments = new HashSet<>();

    public void addAlignment(Alignment alignment) {
        allowedAlignments.add(alignment);
        alignment.getRuleset().add(this);
    }

    public void removeAlignment(Alignment alignment) {
        allowedAlignments.remove(alignment);
        alignment.getRuleset().remove(this);
    }

    public void addRace(Race race) {
        allowedRaces.add(race);
        race.getRuleset().add(this);
    }

    public void removeRace(Race race) {
        allowedRaces.remove(race);
        race.getRuleset().remove(this);
    }


}
