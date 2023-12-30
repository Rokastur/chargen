package com.chargen.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Authority extends BaseEntity {

    private String name;

    @ManyToOne
    private Account account;
}
