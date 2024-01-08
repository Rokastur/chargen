package com.chargen.api.service;

import com.chargen.api.controller.dto.CharacterDto;
import com.chargen.api.entity.Account;
import com.chargen.api.entity.character.Character;
import com.chargen.api.entity.character.*;
import com.chargen.api.entity.character.ability.AbilityScore;
import com.chargen.api.entity.character.ability.EAbility;
import com.chargen.api.repository.AccountRepository;
import com.chargen.api.repository.AlignmentRepository;
import com.chargen.api.repository.CharacterClassRepository;
import com.chargen.api.repository.CharacterRepository;
import com.chargen.api.utility.DiceRoller;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
//TODO: refactor this service. Currently, it does things for many different entities.
// Should contain only strictly Character related methods.
// Make a new Class which combines actions from different services instead.
public class CharacterService {

    private final CharacterRepository characterRepository;

    private final AccountRepository accountRepository;

    private final CharacterClassRepository characterClassRepository;

    private final AlignmentRepository alignmentRepository;

    private final RaceService raceService;

    private final DiceRoller diceRoller = new DiceRoller();

    //TODO: refactor, split into different methods with different responsibilities
    public Character createCharacter(CharacterDto characterDto, Account account) {
        account = accountRepository.findAccountWithCharactersById(account.getId());
        Character character = new Character();
        character.setName(characterDto.getName());

        setCharacterClasses(characterDto, character);
        setAlignment(characterDto, character);
        setAbilityScores(character);
        setRace(characterDto, character);

        account.addCharacter(character);

        return characterRepository.save(character);
    }

    private void setCharacterClasses(CharacterDto characterDto, Character character) {
        List<CharacterClass> characterClasses = new ArrayList<>();
        for (CharacterDto.CharacterClassDto characterClassDto : characterDto.getCharacterClassDtos()) {
            CharacterClass characterClass;
            EClass charClass = characterClassDto.getName();
            characterClass = characterClassRepository.findByName(charClass);
            if (characterClass == null) {
                characterClass = new CharacterClass(charClass);
            }
            characterClass.setLevel(characterClassDto.getLevel());
            characterClass.setExperiencePoints(characterClassDto.getExperience());
            characterClasses.add(characterClass);
        }

        character.setCharacterClass(characterClasses);

    }

    private void setRace(CharacterDto characterDto, Character character) {
        Race race = raceService.findRaceByName(characterDto.getRace());
        race.addCharacter(character);
    }

    private void setAlignment(CharacterDto characterDto, Character character) {
        Alignment alignment = alignmentRepository.findByName(characterDto.getAlignment());
        character.setAlignment(alignment);

    }

    private void setAbilityScores(Character character) {
        for (EAbility EAbility : EAbility.values()) {
            AbilityScore abilityScore = new AbilityScore();
            abilityScore.setEAbility(EAbility);
            int[] diceRollResult = diceRoller.roll(6, 4, true);
            int diceRollSum = Arrays.stream(diceRollResult).sum();
            abilityScore.setScore(diceRollSum);
            character.addAbilityScore(abilityScore);
        }
    }

    public List<CharacterDto> listCharactersByAccount(Account account) {
        List<Character> characters = characterRepository.findByAccount(account);
        List<CharacterDto> characterDtos = new ArrayList<>();
        for (Character character : characters) {
            CharacterDto characterDto = new CharacterDto();
            characterDto.setName(character.getName());
            characterDto.setRace(characterDto.getRace());
            //TODO: temp, fix alignment setting later.
            characterDto.setAlignment(null);
            List<CharacterDto.CharacterClassDto> characterClassDtos = new ArrayList<>();
            for (CharacterClass characterClass : character.getCharacterClass()) {
                CharacterDto.CharacterClassDto characterClassDto = new CharacterDto.CharacterClassDto();
                characterClassDto.setName(characterClass.getName());
                characterClassDto.setLevel(characterClass.getLevel());
                characterClassDto.setExperience(characterClass.getExperiencePoints());
                characterClassDtos.add(characterClassDto);
            }
            characterDto.setCharacterClassDtos(characterClassDtos);
            characterDtos.add(characterDto);
        }
        return characterDtos;
    }
}
