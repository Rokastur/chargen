package com.chargen.api.repository;

import com.chargen.api.entity.character.CharacterClass;
import com.chargen.api.entity.character.EClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterClassRepository extends JpaRepository<CharacterClass, Long> {

    CharacterClass findByName(EClass name);
}
