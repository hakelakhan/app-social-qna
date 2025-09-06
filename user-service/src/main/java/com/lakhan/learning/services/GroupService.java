package com.lakhan.learning.services;

import com.lakhan.learning.dao.GroupDao;
import com.lakhan.learning.dto.CreateNewGroupRequest;
import com.lakhan.learning.entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupDao groupDao;

    @Autowired
    public GroupService(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public Group createGroup(CreateNewGroupRequest request) {
        Group group = new Group();
        // ...populate group fields from request...
        return groupDao.save(group);
    }

    public Group updateGroup(Long groupId, CreateNewGroupRequest request) {
        Group group = groupDao.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        // ...update group fields from request...
        return groupDao.save(group);
    }

    public void deleteGroup(Long groupId) {
        groupDao.deleteById(groupId);
    }

    public void changeGroupOwner(Long groupId, Long newOwnerId) {
        Group group = groupDao.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        // ...change owner logic...
        groupDao.save(group);
    }

    public List<Group> listAllGroups() {
        return groupDao.findAll();
    }

    public List<Group> suggestGroups() {
        // ...suggestion logic...
        return groupDao.findAll(); // placeholder
    }
}

