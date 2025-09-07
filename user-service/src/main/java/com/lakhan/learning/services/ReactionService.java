package com.lakhan.learning.services;

import com.lakhan.learning.dao.ReactionDao;
import com.lakhan.learning.dtos.CreateReactionRequest;
import com.lakhan.learning.dtos.ReactionDto;
import com.lakhan.learning.entities.Reaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReactionService {

    private final ReactionDao reactionDao;

    @Autowired
    public ReactionService(ReactionDao reactionDao) {
        this.reactionDao = reactionDao;
    }

    public ReactionDto addReaction(CreateReactionRequest request) {
        Reaction reaction = new Reaction();
        reaction.setType(request.getType());
        reaction.setUserId(request.getUserId());
        reaction.setPostId(request.getPostId());
        reaction = reactionDao.save(reaction);
        return ReactionDto.fromEntity(reaction);
    }

    public void removeReaction(Long reactionId) {
        reactionDao.deleteById(reactionId);
    }

    public List<ReactionDto> getReactionsForPost(Long postId) {
        return reactionDao.findByPostId(postId).stream().map(ReactionDto::fromEntity).collect(Collectors.toList());
    }
}

