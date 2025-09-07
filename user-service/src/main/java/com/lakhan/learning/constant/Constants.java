package com.lakhan.learning.constant;

public interface Constants {
    interface Messages {
        String USER_NOT_FOUND = "User not found with id: ";
        String GROUP_NOT_FOUND = "Group not found with id: ";
        String EMAIL_ALREADY_EXISTS = "Email already exists: ";
        String INVALID_CREDENTIALS = "Invalid email or password";
        String UNAUTHORIZED_ACCESS = "You are not authorized to access this resource";
        String USER_CREATED_SUCCESSFULLY = "User created successfully";
        String USER_UPDATED_SUCCESSFULLY = "User updated successfully";
        String USER_DELETED_SUCCESSFULLY = "User deleted successfully";
        String GROUP_CREATED_SUCCESSFULLY = "Group created successfully";
        String GROUP_UPDATED_SUCCESSFULLY = "Group updated successfully";
        String GROUP_DELETED_SUCCESSFULLY = "Group deleted successfully";
        String JOINED_GROUP_SUCCESSFULLY = "Joined group successfully";
        String LEFT_GROUP_SUCCESSFULLY = "Left group successfully";
        String ALREADY_A_MEMBER = "You are already a member of this group";
        String NOT_A_MEMBER = "You are not a member of this group";
        String FOLLOWED_USER_SUCCESSFULLY = "Followed user successfully";
        String UNFOLLOWED_USER_SUCCESSFULLY = "Unfollowed user successfully";
        String ALREADY_FOLLOWING = "You are already following this user";
        String NOT_FOLLOWING = "You are not following this user";
        String INVALID_GROUP_TYPE = "Invalid group type. Must be either 'PRIVATE' or 'PUBLIC'";
    }

    String DELIMITER = ",";
}
