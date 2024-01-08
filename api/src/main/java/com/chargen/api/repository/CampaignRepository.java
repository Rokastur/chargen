package com.chargen.api.repository;

import com.chargen.api.entity.Campaign;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    @EntityGraph(attributePaths = {"ruleset"})
    Campaign findCampaignWithRulesetById(Long id);

}
