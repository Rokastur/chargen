package com.chargen.api.entity.items.weapons;

import com.chargen.api.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Property extends BaseEntity {

    private EProperty property;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Weapon weapon;

}