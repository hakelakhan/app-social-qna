package com.lakhan.learning.dtos;

import com.lakhan.learning.entities.Group;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupSuggestionResponse {
    private List<Group> groups;
}

