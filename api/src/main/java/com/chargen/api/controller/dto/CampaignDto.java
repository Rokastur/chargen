package com.chargen.api.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CampaignDto {
    private Long id;
    private String name;
    private String description;
    private List<AccountDto> campaignAccounts;
    private String ruleset;

    public CampaignDto(Long id, String name, String description, String ruleset, List<AccountDto> campaignAccounts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ruleset = ruleset;
        this.campaignAccounts = campaignAccounts;
    }

    @Getter
    @Setter
    public static class AccountDto {
        private Long id;
        private String username;

        public AccountDto(Long id, String username) {
            this.id = id;
            this.username = username;
        }
    }
}

