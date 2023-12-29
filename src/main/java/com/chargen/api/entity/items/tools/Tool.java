package com.chargen.api.entity.items.tools;

import com.chargen.api.entity.items.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("Tool")
public class Tool extends Item {

}
