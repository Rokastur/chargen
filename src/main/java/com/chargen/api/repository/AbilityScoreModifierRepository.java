package com.chargen.api.repository;

import com.chargen.api.entity.character.ability.AbilityScoreModifier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbilityScoreModifierRepository extends JpaRepository<AbilityScoreModifier, Long> {

    AbilityScoreModifier findBySource(String source);
}
