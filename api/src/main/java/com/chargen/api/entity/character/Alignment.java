package com.chargen.api.entity.character;

import com.chargen.api.entity.BaseEntity;
import com.chargen.api.entity.Ruleset;
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
public class Alignment extends BaseEntity {

    private String name;

    @ManyToMany(mappedBy = "allowedAlignments")
    @JsonBackReference
    private Set<Ruleset> ruleset = new HashSet<>();

    //TODO: rethink this. might not be good idea to lose characters if alignment is deleted.
    @OneToMany(
            mappedBy = "alignment",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonBackReference
    private Set<Character> characters = new HashSet<>();


}
