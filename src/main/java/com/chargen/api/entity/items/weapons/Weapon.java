package com.chargen.api.entity.items.weapons;

import com.chargen.api.entity.items.Item;
import com.chargen.api.entity.items.ItemRarity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue("Weapon")
public class Weapon extends Item {

    private int damageDiceCount;

    private int damageDiceRoll;

    @Enumerated(EnumType.STRING)
    private DamageType damageType;

    @Enumerated(EnumType.STRING)
    private ItemRarity rarity;

    @Enumerated(EnumType.STRING)
    private WeaponCategory weaponCategory; //e.g martial

    @Enumerated(EnumType.STRING)
    private WeaponType weaponType; //e.g axe

    @OneToMany(
            mappedBy = "weapon",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private Set<Property> properties;

}
