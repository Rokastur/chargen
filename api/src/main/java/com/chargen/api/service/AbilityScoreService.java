package com.chargen.api.service;

import com.chargen.api.entity.character.Character;
import com.chargen.api.entity.character.ability.AbilityScore;
import com.chargen.api.repository.AbilityScoreRepository;
import com.chargen.api.repository.CharacterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AbilityScoreService {

    private final AbilityScoreRepository abilityScoreRepository;

    private final CharacterRepository characterRepository;

    public List<AbilityScore> findByCharacterId(Long characterId) {
        Character character = characterRepository.getReferenceById(characterId);
        return abilityScoreRepository.findByCharacter(character);
    }
}
