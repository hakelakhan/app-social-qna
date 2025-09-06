package com.lakhan.learning.controllers;

import com.lakhan.learning.dto.CreateNewGroupRequest;
import com.lakhan.learning.services.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody CreateNewGroupRequest request) {
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
    public ResponseEntity<?> suggestGroups() {
        return ResponseEntity.ok(groupService.suggestGroups());
    }
}
