package com.chargen.api.entity.character;

import com.chargen.api.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Language extends BaseEntity {

    private String name;
    private String typicalSpeakers;
    private String script;

    @ManyToMany(mappedBy = "languages")
    private Set<Character> characters = new HashSet<>();

}
