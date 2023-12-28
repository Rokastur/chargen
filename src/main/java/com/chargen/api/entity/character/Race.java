package com.chargen.api.entity.character;

import com.chargen.api.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Race extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Character character;
}
