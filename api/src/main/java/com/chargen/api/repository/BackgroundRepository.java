package com.chargen.api.repository;

import com.chargen.api.entity.character.Background;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackgroundRepository extends JpaRepository<Background, Long> {
}
