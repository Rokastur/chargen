package com.chargen.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Getter
@Setter
public class Role extends BaseEntity{

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Collection<Account> accounts = new HashSet<>();

    public void assignToAccount(Account account) {
        account.getRoles().add(this);
        this.getAccounts().add(account);
    }

    public void removeAccountFromRole(Account account) {
        account.getRoles().remove(this);
        this.getAccounts().remove(account);
    }

    public void removeAllAccountsFromRole() {
        if (this.getAccounts() != null) {
            List<Account> accountList = this.getAccounts().stream().toList();
            accountList.forEach(this :: removeAccountFromRole);
        }
    }
}
