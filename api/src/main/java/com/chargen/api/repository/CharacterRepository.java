package com.chargen.api.repository;

import com.chargen.api.entity.Account;
import com.chargen.api.entity.character.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Long> {

    @Query("SELECT c FROM Character c WHERE c.account = :account")
    List<Character> findByAccount(Account account);


}
