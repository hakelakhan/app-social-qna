package com.lakhan.learning.config;

import com.lakhan.learning.dto.CreateNewGroupRequest;
import com.lakhan.learning.dtos.GroupInfoResponseDto;
import com.lakhan.learning.dtos.UserOnboardingRequest;
import com.lakhan.learning.dtos.UserOnboardingResponse;
import com.lakhan.learning.service.UserOnboardingService;
import com.lakhan.learning.services.GroupService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;

@Configuration
public class DataInitialization {
    @Bean
    CommandLineRunner initSampleData(UserOnboardingService userOnboardingService, JwtUtility jwtUtility, GroupService groupService) {
        return args -> {
            insertUsers(userOnboardingService, jwtUtility);  //will insert user and their interests
            createGroups(groupService); //10 users will create 10 groups
//            addUsersToGroups(); //each group will have 5 users
//            createPosts(); //each user will create 2 posts
//            addLikesAndCommentsToPosts(); //each post will have 5 likes and 3 comments
        };
    }

    private void createGroups(GroupService groupService) {
        //user1 will create group1 and member will be user1,user2,user3,user4,user5,... user 10
        //user11 will create group2 and member will be user11,user12,user13,user14,... user 20
        final int membersInEachGroup = 10;
        for (int i = 1; i <= 91; i+= membersInEachGroup) {
            String groupName = "Group " + i;
            String description = "This is group " + i;
            String creatorUsername = "user" + i;
            Long userId = Long.valueOf(i);

            CreateNewGroupRequest createNewGroupRequest = CreateNewGroupRequest.builder()
                    .name(groupName)
                    .description(description)
                    .groupProfilePictureUrl("http://example.com/group" + i + ".jpg")
                    .groupType("PUBLIC")
                    .interests(Set.of("TECH", "SPORTS"))
                    .build();
            //call service to create group
            GroupInfoResponseDto group = groupService.createGroup(createNewGroupRequest, userId);
            System.out.println("Created group: " + group.getName() + " by user: " + creatorUsername);


        }
    }

    private void insertUsers(UserOnboardingService userOnboardingService, JwtUtility jwtUtility) {
        for (int i = 1; i <= 100; i++) {
            String email = "user" + i + "@example.com";
            String name = "User" + i;
            UserOnboardingResponse userOnboardingResponse = userOnboardingService.signUp(email, name);

            Long userId = userOnboardingResponse.getId();
            String jwtToken =         jwtUtility.generateToken(userOnboardingResponse.getId() + "");
            String username = "user" + i;
            boolean usernameUnique = userOnboardingService.validateUsernameUniqueness(username);
            if(!usernameUnique) {
                throw new RuntimeException("Username " + username + " is not unique");
            }
            UserOnboardingRequest request = UserOnboardingRequest.builder()
                    .userId(userId)
                    .username(username)
                    .name("User " + i)
                    .bio("This is user " + i)
                    .interests("TECH,SPORTS")
                    .build();
            UserOnboardingResponse userOnboardingResponse1 = userOnboardingService.onboardUser(request, "Bearer " + jwtToken);
            System.out.println("Inserted user: " + userOnboardingResponse1.getUsername() + " with interests: " + userOnboardingResponse1.getInterests());
        }

    }

    private void makeUserLoggedIn(String userId) {
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                userId,
                "",
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
