package com.chargen.api.service;

import com.chargen.api.controller.dto.CampaignDto;
import com.chargen.api.entity.Account;
import com.chargen.api.entity.Campaign;
import com.chargen.api.repository.AccountRepository;
import com.chargen.api.repository.CampaignRepository;
import com.chargen.api.security.user.AccountDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;

    private final AccountRepository accountRepository;

    public Campaign createCampaign(CampaignDto dto, AccountDetails accountDetails) {
        Campaign campaign = new Campaign();
        campaign.setName(dto.getName());
        campaign.setDescription(dto.getDescription());
        Long accountId = accountDetails.getAccountId();
        Account account = accountRepository.findOneById(accountId);
        account.addCampaign(campaign);
        return campaignRepository.save(campaign);
    }

    public List<Campaign> viewCampaigns() {
        List<Campaign> campaigns = campaignRepository.findAll();
        return campaigns;
    }

    public CampaignDto viewCampaign(Long id) {
        //TODO: handle optional in a suitable awy
        Campaign campaign = campaignRepository.findById(id).get();
        Set<Account> accounts = campaign.getAccounts();
        List<CampaignDto.AccountDto> accountDtos = new ArrayList<>();
        for (Account account : accounts) {
            CampaignDto.AccountDto accountDto = new CampaignDto.AccountDto(account.getId(), account.getUsername());
            accountDtos.add(accountDto);
        }
        return new CampaignDto(id, campaign.getName(), campaign.getDescription(), accountDtos);
    }
}
