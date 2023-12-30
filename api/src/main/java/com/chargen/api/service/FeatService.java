package com.chargen.api.service;

import com.chargen.api.controller.dto.FeatDto;
import com.chargen.api.entity.character.Feat;
import com.chargen.api.entity.character.ability.AbilityScoreModifier;
import com.chargen.api.repository.FeatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FeatService {

    private final FeatRepository featRepository;

    private final AbilityScoreModifierService abilityScoreModifierService;

    public Feat saveFeat(Feat feat) {
        return featRepository.save(feat);
    }

    public List<Feat> getAll() {
        return featRepository.findAll();
    }

    public Feat createFeat(FeatDto featDto) {
        Feat feat = new Feat();
        feat.setName(feat.getName());
        feat.setDescription(featDto.getDescription());
        feat.setSource(featDto.getSource());

        AbilityScoreModifier abilityScoreModifier;
        if (featDto.getAbility() != null) {
            abilityScoreModifier = new AbilityScoreModifier();
            abilityScoreModifier.setSource(featDto.getName());
            abilityScoreModifier.setModifierValue(featDto.getAbilityIncrease());
            feat.addAbilityScoreModifier(abilityScoreModifier);
        }

        return featRepository.save(feat);
    }
}
