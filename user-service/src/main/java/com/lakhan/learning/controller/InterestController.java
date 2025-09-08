package com.lakhan.learning.controller;

import com.lakhan.learning.entities.Interest;
import com.lakhan.learning.service.InterestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interests")
public class InterestController {

    private final InterestService interestService;

    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @GetMapping("/top10")
    public List<Interest> getTop10Interests() {
        return interestService.getTop10Interests();
    }
}

