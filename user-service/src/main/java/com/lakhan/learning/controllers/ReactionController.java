package com.lakhan.learning.controllers;

import com.lakhan.learning.dtos.CreateReactionRequest;
import com.lakhan.learning.services.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reactions")
public class ReactionController {

    private final ReactionService reactionService;

    @Autowired
    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @PostMapping
    public ResponseEntity<?> addReaction(@RequestBody CreateReactionRequest request) {
        return ResponseEntity.ok(reactionService.addReaction(request));
    }

    @DeleteMapping("/{reactionId}")
    public ResponseEntity<?> removeReaction(@PathVariable Long reactionId) {
        reactionService.removeReaction(reactionId);
        return ResponseEntity.ok("Reaction removed");
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getReactionsForPost(@PathVariable Long postId) {
        return ResponseEntity.ok(reactionService.getReactionsForPost(postId));
    }
}

