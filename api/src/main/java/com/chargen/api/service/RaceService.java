package com.chargen.api.service;

import com.chargen.api.entity.Ruleset;
import com.chargen.api.entity.character.ERace;
import com.chargen.api.entity.character.Race;
import com.chargen.api.repository.RaceRepository;
import com.chargen.api.repository.RulesetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RaceService {

    private final RaceRepository raceRepository;

    private final RulesetRepository rulesetRepository;

    public Race findRaceByName(String raceName) {
        Race race;
        ERace coreRace;
        try {
            coreRace = ERace.valueOf(raceName.toUpperCase());
            race = raceRepository.findByName(coreRace.name());
        } catch (IllegalArgumentException ex) {
            race = raceRepository.findByName(raceName);
        }
        return race;
    }

    public List<Race> listRaceByRuleset(String rulesetName) {
        Ruleset ruleset = rulesetRepository.findByName(rulesetName);
        return raceRepository.findByRuleset(ruleset);
    }
}
