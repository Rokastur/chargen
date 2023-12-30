package com.chargen.api.entity.items.misc;

import com.chargen.api.entity.items.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("Gear")
public class Gear extends Item {
}
