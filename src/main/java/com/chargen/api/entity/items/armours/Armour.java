package com.chargen.api.entity.items.armours;

import com.chargen.api.entity.items.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("Armour")
public class Armour extends Item {

    private ArmourType type;

    private Integer armourClass;



}
