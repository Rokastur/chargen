package com.chargen.api.controller;

import com.chargen.api.controller.dto.AllowedAbilityDto;
import com.chargen.api.service.AbilityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ability")
public class AbilityController {

    private final AbilityService abilityService;

    @GetMapping("/ruleset/{name}")
    public ResponseEntity<?> listAbilitiesByRuleset(@PathVariable(name = "name") String rulesetName) {
        List<AllowedAbilityDto> abilities = abilityService.findAvailableByRuleset(rulesetName);
        return ResponseEntity.ok(abilities);
    }
}
