package com.chargen.api.repository;

import com.chargen.api.entity.character.Character;
import com.chargen.api.entity.character.ability.AbilityScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbilityScoreRepository extends JpaRepository<AbilityScore, Long> {

    @Query("SELECT a FROM AbilityScore a WHERE a.character = :character")
    List<AbilityScore> findByCharacter(Character character);

}
