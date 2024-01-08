package com.chargen.api.controller;

import com.chargen.api.entity.character.Alignment;
import com.chargen.api.service.AlignmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/alignments")
public class AlignmentController extends AuthenticationHelper {

    private final AlignmentService alignmentService;

    @GetMapping("/ruleset/{name}")
    public ResponseEntity<?> listAvailableAlignmentsByRuleset(@PathVariable("name") String rulesetName) {
        List<Alignment> alignments = alignmentService.listAlignmentsByRuleset(rulesetName);
        return ResponseEntity.ok(alignments);
    }
}
