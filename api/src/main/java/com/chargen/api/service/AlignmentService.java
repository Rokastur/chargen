package com.chargen.api.service;

import com.chargen.api.entity.Ruleset;
import com.chargen.api.entity.character.Alignment;
import com.chargen.api.repository.AlignmentRepository;
import com.chargen.api.repository.RulesetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlignmentService {

    private final AlignmentRepository alignmentRepository;

    private final RulesetRepository rulesetRepository;

    public List<Alignment> listAlignmentsByRuleset(String rulesetName) {
        Ruleset ruleset = rulesetRepository.findByName(rulesetName);
        return alignmentRepository.findByRuleset(ruleset);
    }
}
