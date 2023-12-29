package com.chargen.api.entity.items.edibles;

import com.chargen.api.entity.items.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("Edible")
public class Edible extends Item {


}
