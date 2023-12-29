package com.chargen.api.entity.items.weapons;

import com.chargen.api.entity.items.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("Ammunition")
public class Ammunition extends Item {
}
