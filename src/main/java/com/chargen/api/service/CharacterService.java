package com.chargen.api.service;

import com.chargen.api.controller.dto.CharacterDto;
import com.chargen.api.entity.Account;
import com.chargen.api.entity.character.Character;
import com.chargen.api.entity.character.CharacterClass;
import com.chargen.api.repository.AccountRepository;
import com.chargen.api.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    private final AccountRepository accountRepository;

    public CharacterService(CharacterRepository characterRepository, AccountRepository accountRepository) {
        this.characterRepository = characterRepository;
        this.accountRepository = accountRepository;
    }

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
        return characterRepository.save(character);
    }
}
