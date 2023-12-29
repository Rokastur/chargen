package com.chargen.api.entity.items.gaming;

import com.chargen.api.entity.items.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("Gaming_Set")
public class GamingSet extends Item {
}
