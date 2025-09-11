package com.lakhan.learning.controllers;

import com.lakhan.learning.config.JwtUtility;
import com.lakhan.learning.dto.CreateNewGroupRequest;
import com.lakhan.learning.dtos.GroupSuggestionResponse;
import com.lakhan.learning.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;
    private final JwtUtility jwtUtility;

    @Autowired
    public GroupController(GroupService groupService, JwtUtility jwtUtility) {
        this.groupService = groupService;
        this.jwtUtility = jwtUtility;
    }

    @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody CreateNewGroupRequest request, @RequestHeader("Authorization") String authHeader) {

        Long userId = jwtUtility.extractUserIdFromAuthHeader(authHeader);
        return ResponseEntity.ok(groupService.createGroup(request, userId));
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<?> updateGroup(@PathVariable Long groupId, @RequestBody CreateNewGroupRequest request, @RequestHeader("Authorization") String authHeader) {
        Long userId = jwtUtility.extractUserIdFromAuthHeader(authHeader);
        return ResponseEntity.ok(groupService.updateGroup(groupId, request, userId));
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
    public ResponseEntity<GroupSuggestionResponse> suggestGroups(@RequestHeader("Authorization") String authHeader) {
        Long userId = jwtUtility.extractUserIdFromAuthHeader(authHeader);
        GroupSuggestionResponse response = groupService.suggestGroups(userId);
        return ResponseEntity.ok(response);
    }
}
