package com.chargen.api.entity.items.tools;

import com.chargen.api.entity.character.Background;
import com.chargen.api.entity.items.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue("Tool")
public class Tool extends Item {

    @ManyToMany(mappedBy = "toolProficiencies")
    private Set<Background> backgrounds = new HashSet<>();

}
