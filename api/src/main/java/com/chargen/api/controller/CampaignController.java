package com.chargen.api.controller;

import com.chargen.api.controller.dto.CampaignDto;
import com.chargen.api.entity.Campaign;
import com.chargen.api.security.user.AccountDetails;
import com.chargen.api.service.CampaignService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaigns")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CampaignController {

    private final CampaignService campaignService;

    @PostMapping("/new")
    public ResponseEntity<?> createCampaign(@RequestBody CampaignDto campaignDto) {
        AccountDetails accountDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Campaign campaign = campaignService.createCampaign(campaignDto, accountDetails);
        return ResponseEntity.ok(campaign);
    }

    @GetMapping("/all")
    public ResponseEntity<?> viewCampaigns() {
        List<Campaign> campaigns = campaignService.viewCampaigns();
        return ResponseEntity.ok(campaigns);
    }

    @GetMapping("/campaign/{id}")
    public ResponseEntity<?> viewCampaign(@PathVariable("id") long id) {
        CampaignDto campaign = campaignService.viewCampaign(id);
        return ResponseEntity.ok(campaign);
    }
}
