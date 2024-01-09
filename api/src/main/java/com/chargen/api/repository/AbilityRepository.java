package com.chargen.api.repository;

import com.chargen.api.controller.dto.AllowedAbilityDto;
import com.chargen.api.entity.Ruleset;
import com.chargen.api.entity.character.ability.Ability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AbilityRepository extends JpaRepository<Ability, Long> {

    @Query("""
            select new com.chargen.api.controller.dto.AllowedAbilityDto(a.name)
                from Ability a
                join a.ruleset r
                WHERE :ruleset MEMBER OF a.ruleset
            """)
    List<AllowedAbilityDto> findByRuleset(@Param("ruleset") Ruleset ruleset);
}
