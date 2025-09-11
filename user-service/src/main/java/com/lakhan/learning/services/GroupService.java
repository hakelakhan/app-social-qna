package com.lakhan.learning.services;

import com.lakhan.learning.dao.GroupDao;
import com.lakhan.learning.dao.InterestDao;
import com.lakhan.learning.dto.CreateNewGroupRequest;
import com.lakhan.learning.dtos.GroupInfoResponseDto;
import com.lakhan.learning.dtos.GroupSuggestionResponse;
import com.lakhan.learning.entities.Group;
import com.lakhan.learning.entities.Interest;
import com.lakhan.learning.entities.User;
import com.lakhan.learning.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.lakhan.learning.constant.Constants.DELIMITER;

@Service
public class GroupService {

    private final GroupDao groupDao;
    private final UserRepository userRepository;
    private final InterestDao interestDao;

    @Autowired
    public GroupService(GroupDao groupDao, UserRepository userRepository, InterestDao interestDao) {
        this.groupDao = groupDao;
        this.userRepository = userRepository;
        this.interestDao = interestDao;
    }

    @Transactional
    public GroupInfoResponseDto createGroup(CreateNewGroupRequest request, Long userId) {

        Set<Interest> interests = getInterestsFromDb(request.getInterests());
        Group group = new Group();
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        group.setCreatedBy(user);
        group.setGroupType(request.getGroupType());
        group.setGroupInterests(interests);
        group.setName(request.getName());
        group.setDescription(request.getDescription());
        group.setProfilePicPath(request.getGroupProfilePictureUrl());
        group.getMembers().add(user); // Add creator as a member
        Group savedGroup = groupDao.save(group);
        return GroupInfoResponseDto.toDto(savedGroup);
    }

    private Set<Interest> getInterestsFromDb(String delimitedInterestStrings) {
        Set<String> interestsString = Optional.ofNullable(delimitedInterestStrings)
                .map(s -> Arrays.stream(s.split(DELIMITER))
                        .map(String::trim)
                        .filter(name -> !name.isEmpty())
                        .collect(Collectors.toSet()))
                .orElse(Collections.emptySet());
        return getInterestsFromDb(interestsString);
    }
    private Set<Interest> getInterestsFromDb(Set<String> interestsString) {
        Set<Interest> interests = Optional.ofNullable(interestsString).orElse(Collections.emptySet()).stream()
                .map(String::trim)
                .filter(name -> !name.isEmpty())
                .map(name -> interestDao.findByName(name)
                        .orElseGet(() -> {
                            Interest newInterest = new Interest();
                            newInterest.setName(name);
                            return interestDao.save(newInterest); // persist new
                        })
                )
                .collect(Collectors.toSet());
        return interests;
    }

    @Transactional
    public GroupInfoResponseDto updateGroup(Long groupId, CreateNewGroupRequest request, Long userId) {
        Set<Interest> interests = getInterestsFromDb(request.getInterests());
        Group group = groupDao.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        group.setGroupType(request.getGroupType());
        group.setGroupInterests(interests);
        group.setName(request.getName());
        group.setDescription(request.getDescription());
        group.setProfilePicPath(request.getGroupProfilePictureUrl());
        group.getMembers().add(user); // Add creator as a member
        Group savedGroup = groupDao.save(group);
        return GroupInfoResponseDto.toDto(savedGroup);
    }

    public void deleteGroup(Long groupId) {
        groupDao.deleteById(groupId);
    }

    public void changeGroupOwner(Long groupId, Long newOwnerId) {
        Group group = groupDao.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        User newOwner = userRepository.findById(newOwnerId).orElseThrow(() -> new RuntimeException("User not found"));
        group.setCreatedBy(newOwner);
        groupDao.save(group);
    }

    public List<GroupInfoResponseDto> listAllGroups() {
        return groupDao.findAll().stream()
                .map(g -> new GroupInfoResponseDto().toDto(g))
                .toList();
    }

    public GroupSuggestionResponse suggestGroups(Long userId) {
        List<Group> suggestedGroups;
        if (userId != null) {
            User user = userRepository.findById(userId).orElse(null);
            if (user != null && user.getInterests() != null && !user.getInterests().isEmpty()) {
                // Find groups matching user's interests
                suggestedGroups = groupDao.findAll().stream()
                        .filter(group -> group.getGroupInterests().stream().anyMatch(tag ->
                                user.getInterests().stream().anyMatch(interest ->
                                        tag.getName().equalsIgnoreCase(interest.getName())
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
        return new GroupSuggestionResponse(suggestedGroups.stream().map(g -> new GroupInfoResponseDto().toDto(g)).toList());
    }
}
