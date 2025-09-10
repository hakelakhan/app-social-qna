package com.lakhan.learning.controllers;

import com.lakhan.learning.dto.CreateNewGroupRequest;
import com.lakhan.learning.services.GroupService;
import com.lakhan.learning.dtos.GroupSuggestionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody CreateNewGroupRequest request, @AuthenticationPrincipal OAuth2User principal) {
        return ResponseEntity.ok(groupService.createGroup(request));
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<?> updateGroup(@PathVariable Long groupId, @RequestBody CreateNewGroupRequest request) {
        return ResponseEntity.ok(groupService.updateGroup(groupId, request));
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long groupId) {
        groupService.deleteGroup(groupId);
        return ResponseEntity.ok("Group deleted successfully");
    }

    @PatchMapping("/{groupId}/owner")
    public ResponseEntity<?> changeGroupOwner(@PathVariable Long groupId, @RequestParam Long newOwnerId) {
        groupService.changeGroupOwner(groupId, newOwnerId);
        return ResponseEntity.ok("Group owner changed successfully");
    }

    @GetMapping
    public ResponseEntity<?> listAllGroups() {
        return ResponseEntity.ok(groupService.listAllGroups());
    }

    @GetMapping("/suggestions")
    public ResponseEntity<GroupSuggestionResponse> suggestGroups() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = null;
        if (authentication != null && authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.UserDetails) {
            // ...extract userId from principal if available...
            // If you store userId in principal, extract here. Otherwise, adapt as needed.
        }
        // For demo, assume userId is available as a custom claim or attribute
        GroupSuggestionResponse response = groupService.suggestGroups(userId);
        return ResponseEntity.ok(response);
    }
}
