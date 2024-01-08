package com.chargen.api.repository;

import com.chargen.api.entity.Ruleset;
import com.chargen.api.entity.character.Race;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RaceRepository extends JpaRepository<Race, Long> {

    Race findByName(String race);

    List<Race> findByRuleset(Ruleset ruleset);

}
