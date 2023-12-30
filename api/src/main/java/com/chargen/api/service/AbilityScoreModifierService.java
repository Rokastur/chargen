package com.chargen.api.service;

import com.chargen.api.repository.AbilityScoreModifierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AbilityScoreModifierService {

    private final AbilityScoreModifierRepository abilityScoreModifierRepository;


}
