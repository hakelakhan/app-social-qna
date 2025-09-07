package com.lakhan.learning.services;

import com.lakhan.learning.dao.GroupDao;
import com.lakhan.learning.dto.CreateNewGroupRequest;
import com.lakhan.learning.dtos.GroupSuggestionResponse;
import com.lakhan.learning.entities.Group;
import com.lakhan.learning.entities.User;
import com.lakhan.learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupDao groupDao;
    private final UserRepository userRepository;

    @Autowired
    public GroupService(GroupDao groupDao, UserRepository userRepository) {
        this.groupDao = groupDao;
        this.userRepository = userRepository;
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

    public GroupSuggestionResponse suggestGroups(Long userId) {
        List<Group> suggestedGroups;
        if (userId != null) {
            User user = userRepository.findById(userId).orElse(null);
            if (user != null && user.getInterests() != null && !user.getInterests().isEmpty()) {
                // Find groups matching user's interests
                suggestedGroups = groupDao.findAll().stream()
                        .filter(group -> group.getTags().stream().anyMatch(tag ->
                                user.getInterests().stream().anyMatch(interest ->
                                        tag.equalsIgnoreCase(interest.name())
                                )
                        ))
                        .limit(5)
                        .toList();
            } else {
                suggestedGroups = groupDao.findAll().stream().limit(5).toList();
            }
        } else {
            suggestedGroups = groupDao.findAll().stream().limit(5).toList();
        }
        return new GroupSuggestionResponse(suggestedGroups);
    }
}
