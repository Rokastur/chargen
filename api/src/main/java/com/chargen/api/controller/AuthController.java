package com.chargen.api.controller;

import com.chargen.api.entity.Account;
import com.chargen.api.security.jwt.JwtUtils;
import com.chargen.api.security.user.AccountDetails;
import com.chargen.api.service.AccountService;
import com.chargen.api.service.dto.AccountDto;
import com.chargen.api.service.dto.JwtResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody AccountDto accountDto) {
        accountService.createNewAccount(accountDto);
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateAccount(@RequestBody AccountDto accountDto) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountDto.getUsername(), accountDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtTokenForUser(authentication);
        AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
        List<String> roles = accountDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return ResponseEntity.ok(new JwtResponse(accountDetails.getUsername(), jwt, roles));
    }

    //TODO: remove later. temp method to test things
    @GetMapping("/accounts")
    public ResponseEntity<?> displayAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }
}
