package com.chargen.api.repository;

import com.chargen.api.entity.Ruleset;
import com.chargen.api.entity.character.Alignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlignmentRepository extends JpaRepository<Alignment, Long> {

    List<Alignment> findByRuleset(Ruleset ruleset);

    Alignment findByName(String name);
}
