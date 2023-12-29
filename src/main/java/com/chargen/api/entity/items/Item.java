package com.chargen.api.entity.items;

import com.chargen.api.entity.BaseEntity;
import com.chargen.api.entity.Source;
import com.chargen.api.entity.character.Background;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Item_Type")
public abstract class Item extends BaseEntity {

    private String name;

    private BigDecimal weight;

    private BigDecimal cost;

    @Enumerated(EnumType.STRING)
    private Source source;

    @ManyToMany(mappedBy = "items")
    private Set<Background> backgrounds = new HashSet<>();

}
