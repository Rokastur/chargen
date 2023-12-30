package com.chargen.api.service;

import com.chargen.api.entity.character.ERace;
import com.chargen.api.entity.character.Race;
import com.chargen.api.repository.RaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RaceService {

    private final RaceRepository raceRepository;

    public Race findRaceByName(String raceName) {
        Race race;
        ERace coreRace;
        try {
            coreRace = ERace.valueOf(raceName.toUpperCase());
            race = raceRepository.findByRace(coreRace.name());
        } catch (IllegalArgumentException ex) {
            race = raceRepository.findByRace(raceName);
        }
        return race;
    }

    public Race saveRace(Race race) {
        return raceRepository.save(race);
    }
}
