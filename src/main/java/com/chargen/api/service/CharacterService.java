package com.chargen.api.service;

import com.chargen.api.controller.dto.CharacterDto;
import com.chargen.api.entity.Account;
import com.chargen.api.entity.character.Character;
import com.chargen.api.entity.character.CharacterClass;
import com.chargen.api.entity.character.ability.Ability;
import com.chargen.api.entity.character.ability.AbilityScore;
import com.chargen.api.repository.AccountRepository;
import com.chargen.api.repository.CharacterRepository;
import com.chargen.api.utility.DiceRoller;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;

    private final AccountRepository accountRepository;

    private final DiceRoller diceRoller = new DiceRoller();

    public Character createCharacter(CharacterDto characterDto, String username) {
        Account account = accountRepository.findByUsername(username).get(); //TODO: deal with optional
        Character character = new Character();
        character.setName(characterDto.getName());
        for (CharacterDto.CharacterClassDto characterClassDto : characterDto.getCharacterClassDtos()) {
            CharacterClass characterClass = new CharacterClass();
            characterClass.setName(characterClassDto.getName());
            characterClass.setLevel(characterClassDto.getLevel());
            characterClass.setExperiencePoints(characterClassDto.getExperience());
            account.addCharacter(character);
        }

        for (Ability ability : Ability.values()) {
            AbilityScore abilityScore = new AbilityScore();
            abilityScore.setAbility(ability);
            int[] diceRollResult = diceRoller.roll(6, 4, true);
            int diceRollSum = Arrays.stream(diceRollResult).sum();
            abilityScore.setScore(diceRollSum);
            character.addAbilityScore(abilityScore);
        }

        return characterRepository.save(character);
    }
}
