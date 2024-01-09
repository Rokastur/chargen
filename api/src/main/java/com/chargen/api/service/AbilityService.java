package com.chargen.api.service;

import com.chargen.api.controller.dto.AllowedAbilityDto;
import com.chargen.api.entity.Ruleset;
import com.chargen.api.repository.AbilityRepository;
import com.chargen.api.repository.RulesetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AbilityService {

    private final RulesetRepository rulesetRepository;

    private final AbilityRepository abilityRepository;

    public List<AllowedAbilityDto> findAvailableByRuleset(String rulesetName) {
        Ruleset ruleset = rulesetRepository.findByName(rulesetName);
        return abilityRepository.findByRuleset(ruleset);
    }
}
