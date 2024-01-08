package com.chargen.api.controller;

import com.chargen.api.controller.dto.CharacterDto;
import com.chargen.api.entity.Account;
import com.chargen.api.entity.character.Character;
import com.chargen.api.service.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController extends AuthenticationHelper {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createCharacter(@RequestBody CharacterDto characterDto) {
        Account account = getAuthenticatedAccount();
        Character character = characterService.createCharacter(characterDto, account);
        return ResponseEntity.ok(character);
    }

    @GetMapping("/owned")
    public ResponseEntity<?> listCharacters() {
        Account account = getAuthenticatedAccount();
        List<CharacterDto> characters = characterService.listCharactersByAccount(account);
        return ResponseEntity.ok(characters);
    }
}
