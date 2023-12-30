package com.chargen.api.controller;

import com.chargen.api.controller.dto.FeatDto;
import com.chargen.api.entity.character.Feat;
import com.chargen.api.service.FeatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feat")
@AllArgsConstructor
public class FeatController {

    private final FeatService featService;

    @PostMapping("/new")
    public ResponseEntity<?> createFeat(@RequestBody FeatDto featDto) {
        Feat feat = featService.createFeat(featDto);
        return ResponseEntity.ok(feat);
    }

    @GetMapping("/all")
    public ResponseEntity<?> showAll() {
        List<Feat> feats = featService.getAll();
        return ResponseEntity.ok(feats);
    }
}

