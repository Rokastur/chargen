package com.chargen.api.entity;

import com.chargen.api.entity.character.Character;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
@NamedEntityGraph(name = "graph.Account.campaigns",
        attributeNodes = @NamedAttributeNode("campaigns"))
public class Account extends BaseEntity {

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "account_roles",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles = new HashSet<>();

    @OneToMany(
            mappedBy = "account",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Character> characters = new ArrayList<>();

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Set<Authority> authorities;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "account_campaigns",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    @JsonManagedReference
    private Set<Campaign> campaigns = new HashSet<>();

    public void addCharacter(Character character) {
        characters.add(character);
        character.setAccount(this);
    }

    public void removeCharacter(Character character) {
        characters.remove(character);
        character.setAccount(null);
    }

    public void addCampaign(Campaign campaign) {
        campaigns.add(campaign);
        campaign.getAccounts().add(this);
    }

    public void removeCampaign(Campaign campaign) {
        campaigns.remove(campaign);
        campaign.getAccounts().remove(this);
    }

}
