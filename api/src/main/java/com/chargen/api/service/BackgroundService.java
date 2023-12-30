package com.chargen.api.service;

import com.chargen.api.repository.BackgroundRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BackgroundService {

    private final BackgroundRepository backgroundRepository;

}
