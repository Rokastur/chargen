package com.chargen.api.repository;

import com.chargen.api.entity.character.Race;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository extends JpaRepository<Race, Long> {

    Race findByRace(String race);
}
