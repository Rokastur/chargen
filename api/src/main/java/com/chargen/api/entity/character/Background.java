package com.chargen.api.entity.character;

import com.chargen.api.entity.BaseEntity;
import com.chargen.api.entity.Ruleset;
import com.chargen.api.entity.items.Item;
import com.chargen.api.entity.items.tools.Tool;
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
public class Background extends BaseEntity {

    private String name;

    private String description;

    private String backgroundFeature;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "background_items",
            joinColumns = @JoinColumn(name = "background_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    @JsonManagedReference
    private Set<Item> items = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "background_skills",
            joinColumns = @JoinColumn(name = "background_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    @JsonManagedReference
    private Set<Skill> skillProficiencies = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "background_tools",
            joinColumns = @JoinColumn(name = "background_id"),
            inverseJoinColumns = @JoinColumn(name = "tool_id"))
    @JsonManagedReference
    private Set<Tool> toolProficiencies = new HashSet<>();

    @ManyToMany(mappedBy = "allowedBackgrounds")
    @JsonBackReference
    private Set<Ruleset> rulesets = new HashSet<>();
}
