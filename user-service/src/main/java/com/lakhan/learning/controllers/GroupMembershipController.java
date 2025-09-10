package com.lakhan.learning.controllers;

import com.lakhan.learning.services.GroupMembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class GroupMembershipController {

    private final GroupMembershipService groupMembershipService;

    @Autowired
    public GroupMembershipController(GroupMembershipService groupMembershipService) {
        this.groupMembershipService = groupMembershipService;
    }

    @PostMapping("/{groupId}/join")
    public ResponseEntity<?> joinGroup(@PathVariable Long groupId, @RequestParam Long userId) {
        groupMembershipService.joinGroup(groupId, userId);
        return ResponseEntity.ok("User joined the group successfully");
    }

    @PostMapping("/{groupId}/leave")
    public ResponseEntity<?> leaveGroup(@PathVariable Long groupId, @RequestParam Long userId) {
        groupMembershipService.leaveGroup(groupId, userId);
        return ResponseEntity.ok("User left the group successfully");
    }

    @GetMapping("/{groupId}/members")
    public ResponseEntity<?> listGroupMembers(@PathVariable Long groupId) {
        return ResponseEntity.ok(groupMembershipService.listGroupMembers(groupId));
    }

    @GetMapping("/users/{userId}/groups")
    public ResponseEntity<?> listUserGroups(@PathVariable Long userId) {
        return ResponseEntity.ok(groupMembershipService.listUserGroups(userId));
    }
}
