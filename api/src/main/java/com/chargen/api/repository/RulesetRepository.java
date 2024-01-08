package com.chargen.api.repository;

import com.chargen.api.entity.Ruleset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RulesetRepository extends JpaRepository<Ruleset, Long> {

    Ruleset findByName(String name);

}