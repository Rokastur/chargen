package com.chargen.api.entity.character;

import com.chargen.api.entity.Account;
import com.chargen.api.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Character extends BaseEntity {

    private String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CharacterClass> characterClass;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;


}
