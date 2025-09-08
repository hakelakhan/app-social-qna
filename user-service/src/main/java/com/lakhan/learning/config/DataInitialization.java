package com.lakhan.learning.config;

import com.lakhan.learning.dtos.UserOnboardingRequest;
import com.lakhan.learning.service.UserOnboardingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    private void insertUsers(UserOnboardingService userOnboardingService) {
        for (int i = 1; i <= 100; i++) {
            UserOnboardingRequest request = UserOnboardingRequest.builder()
                    .email("user" + i + "@example.com")
                    .username("user" + i)
                    .name("User " + i)
                    .bio("This is user " + i)
                    .interests("TECH,SPORTS")
                    .build();
            userOnboardingService.onboardUser(request);
        }

    }
}
