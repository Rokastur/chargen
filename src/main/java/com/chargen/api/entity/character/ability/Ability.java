package com.chargen.api.entity.character.ability;

import com.chargen.api.entity.BaseEntity;
import com.chargen.api.entity.character.CharacterClass;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Ability extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private EAbility ability;

    @ManyToMany(mappedBy = "abilities")
    private Set<CharacterClass> characterClasses = new HashSet<>();
}
