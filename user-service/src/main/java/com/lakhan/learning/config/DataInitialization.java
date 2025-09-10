package com.lakhan.learning.config;

import com.lakhan.learning.dtos.UserOnboardingRequest;
import com.lakhan.learning.service.UserOnboardingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataInitialization {
    @Bean
    CommandLineRunner initSampleData(UserOnboardingService userOnboardingService) {
        return args -> {
            insertUsers(userOnboardingService);  //will insert user and their interests
//            createGroups(); //10 users will create 10 groups
//            addUsersToGroups(); //each group will have 5 users
//            createPosts(); //each user will create 2 posts
//            addLikesAndCommentsToPosts(); //each post will have 5 likes and 3 comments
        };
    }

    private void createGroups() {
        //user1 will create group1 and member will be user1,user2,user3,user4,user5,... user 10
        //user11 will create group2 and member will be user11,user12,user13,user14,... user 20
        final int membersInEachGroup = 10;
        for (int i = 1; i <= 91; i+= membersInEachGroup) {
            String groupName = "Group " + i;
            String description = "This is group " + i;
            String creatorUsername = "user" + i;
            //members
            Set<String> members = new HashSet<>();
            for (int j = i +1 ; j < i + membersInEachGroup; j++) {
                members.add("user" + j);
            }
            //call service to create group

        }
    }

    private void insertUsers(UserOnboardingService userOnboardingService) {
        for (int i = 1; i <= 100; i++) {
            UserOnboardingRequest request = UserOnboardingRequest.builder()
                    .email("user" + i + "@example.com")
                    .username("user" + i)
                    .name("User " + i)
                    .bio("This is user " + i)
                    .interests("TECH,SPORTS")
                    .build();
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                    "user" + i, null, null ));
            userOnboardingService.onboardUser(request);
        }

    }
}
