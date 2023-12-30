package com.chargen.api.repository;

import com.chargen.api.entity.character.Feat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatRepository extends JpaRepository<Feat, Long> {
}
