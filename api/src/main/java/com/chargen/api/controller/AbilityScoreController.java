package com.chargen.api.controller;

import com.chargen.api.entity.character.ability.AbilityScore;
import com.chargen.api.service.AbilityScoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ability_score")
public class AbilityScoreController {

    private final AbilityScoreService abilityScoreService;

    @GetMapping("character/{characterId}")
    public ResponseEntity<?> listAbilityScoresByCharacter(@PathVariable(name = "characterId") Long characterId) {
        List<AbilityScore> abilityScores = abilityScoreService.findByCharacterId(characterId);
        return ResponseEntity.ok(abilityScores);
    }
}
