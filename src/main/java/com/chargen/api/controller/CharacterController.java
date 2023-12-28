package com.chargen.api.controller;

import com.chargen.api.controller.dto.CharacterDto;
import com.chargen.api.entity.character.Character;
import com.chargen.api.security.user.AccountDetails;
import com.chargen.api.service.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/character")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createCharacter(@RequestBody CharacterDto characterDto) {
        AccountDetails accountDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Character character = characterService.createCharacter(characterDto, accountDetails.getUsername());
        return ResponseEntity.ok(character);
    }
}
