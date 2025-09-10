package com.lakhan.learning.services;

import com.lakhan.learning.dao.GroupDao;
import com.lakhan.learning.entities.Group;
import com.lakhan.learning.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupMembershipService {

    private final GroupDao groupDao;

    @Autowired
    public GroupMembershipService(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public void joinGroup(Long groupId, Long userId) {
        Group group = groupDao.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        User user = new User();
        user.setId(userId);
        group.getMembers().add(user);
        groupDao.save(group);
    }

    public void leaveGroup(Long groupId, Long userId) {
        Group group = groupDao.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        group.getMembers().removeIf(u -> u.getId().equals(userId));
        groupDao.save(group);
    }

    public Set<User> listGroupMembers(Long groupId) {
        Group group = groupDao.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        return group.getMembers();
    }

    public Set<Group> listUserGroups(Long userId) {
        return groupDao.findAll().stream()
                .filter(g -> g.getMembers().stream().anyMatch(u -> u.getId().equals(userId)))
                .collect(Collectors.toSet());
    }
}

