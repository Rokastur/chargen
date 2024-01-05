package com.chargen.api.entity;

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
public class Campaign extends BaseEntity {

    private String name;

    private String description;

    //TODO consider adding relationships to denote which accounts are DMs of specific campaign, and which are players.

    @ManyToMany(mappedBy = "campaigns")
    @JsonBackReference
    private Set<Account> accounts = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "campaign_characters",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    @JsonManagedReference
    private Set<Character> characters = new HashSet<>();


}
