package com.lakhan.learning.dtos;

import java.util.Set;

public enum Interest {
    MOVIES("Movies"),
    FITNESS("Fitness"),
    CAREER("Career"),
    CODING("Coding"),
    FAMILY("Family"),
    TRAVEL("Travel"),
    FOOD("Food"),
    MUSIC("Music"),
    ART("Art"),
    SPORTS("Sports"),
    READING("Reading"),
    GAMING("Gaming"),
    UNKNOWN("Unknown");

    private String interestName;

    Interest(String interestName) {
        this.interestName = interestName;
    }

    //method that converts a comma-separated string of interests into a Set of Interest enums
    public static Set<Interest> valueOf(String interests, String delimiter) {

        String[] interestArray = interests.split(delimiter);
        Set<Interest> interestSet = java.util.Arrays.stream(interestArray)
                .map(String::trim) // Trim whitespace
                .map(Interest::fromString)
                .collect(java.util.stream.Collectors.toSet());
        return interestSet;

    }

    public String getInterestName() {
        return interestName;
    }

    // ✅ Conversion method
    public static Interest fromString(String name) {
        if (name == null || name.isBlank()) {
            return UNKNOWN;
        }
        for (Interest interest : Interest.values()) {
            if (interest.interestName.equalsIgnoreCase(name)) {
                return interest;
            }
        }
        return UNKNOWN; // fallback if no match found
    }
}