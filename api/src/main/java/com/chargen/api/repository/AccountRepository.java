package com.chargen.api.repository;

import com.chargen.api.entity.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("""
            SELECT a FROM Account a WHERE a.username = :username
            """)
    Optional<Account> findByUsername(String username);

    @EntityGraph(value = "graph.Account.campaigns")
    Account findOneById(Long id);

    @EntityGraph(attributePaths = {"characters"})
    Account findAccountWithCharactersById(Long id);

}
