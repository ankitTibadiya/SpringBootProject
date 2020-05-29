package com.arthur.blogP.model;

import com.arthur.blogP.exception.SpringBlogPException;

import java.util.Arrays;

public enum VoteType {
    UPVOTE(1), DOWNVOTE(-1);

    private int direction;
    VoteType(int direction){}

    public static VoteType lookup(Integer direction) {
        return Arrays.stream(VoteType.values())
                .filter(value -> value.getDirection().equals(direction))
                .findAny()
                .orElseThrow(() -> new SpringBlogPException("Vote not found"));
    }

    public Integer getDirection() {
        return direction;
    }
}
