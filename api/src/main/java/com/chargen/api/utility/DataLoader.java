package com.chargen.api.utility;

import com.chargen.api.entity.Ruleset;
import com.chargen.api.entity.character.Alignment;
import com.chargen.api.entity.character.EAlignment;
import com.chargen.api.entity.character.ERace;
import com.chargen.api.entity.character.Race;
import com.chargen.api.entity.character.ability.Ability;
import com.chargen.api.entity.character.ability.EAbility;
import com.chargen.api.repository.RulesetRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

@Component
@AllArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final RulesetRepository rulesetRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Ruleset ruleset = new Ruleset();
        ruleset.setName("DND5e");
        createAlignments(ruleset);
        createRaces(ruleset);
        createAbilities(ruleset);
        rulesetRepository.save(ruleset);
    }

    private void createAlignments(Ruleset ruleset) {

        for (EAlignment eAlignment : EnumSet.allOf(EAlignment.class)) {
            Alignment alignment = new Alignment();
            alignment.setName(eAlignment.name());
            ruleset.addAlignment(alignment);
        }
    }

    private void createRaces(Ruleset ruleset) {
        for (ERace eRace : EnumSet.allOf(ERace.class)) {
            Race race = new Race();
            race.setName(eRace.name());
            ruleset.addRace(race);
        }
    }

    private void createAbilities(Ruleset ruleset) {
        for (EAbility eAbility : EnumSet.allOf(EAbility.class)) {
            Ability ability = new Ability();
            ability.setName(eAbility.name());
            ruleset.addAbility(ability);
        }
    }
}
