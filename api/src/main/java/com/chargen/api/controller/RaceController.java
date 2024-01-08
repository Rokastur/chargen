package com.chargen.api.controller;


import com.chargen.api.entity.character.Race;
import com.chargen.api.service.RaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/race")
public class RaceController extends AuthenticationHelper {

    private final RaceService raceService;

    @GetMapping("/ruleset/{name}")
    public ResponseEntity<?> listAvailableAlignmentsByRuleset(@PathVariable("name") String rulesetName) {
        List<Race> raceList = raceService.listRaceByRuleset(rulesetName);
        return ResponseEntity.ok(raceList);
    }
}
