package com.arthur.blogP.service;


import com.arthur.blogP.exception.SpringBlogPException;
import com.arthur.blogP.model.Subreddit;
import com.arthur.blogP.repository.SubredditRepository;
import com.arthur.blogP.dto.SubredditDto;
import com.arthur.blogP.mapper.SubredditMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto createSubreddit(SubredditDto subredditDto){
        Subreddit savedSubreddit = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(savedSubreddit.getId());
        return subredditDto;
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> showAllPosts() {
        return subredditRepository.findAll()
        .stream().map(subredditMapper::mapSubredditToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public SubredditDto getSubredditById(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringBlogPException("Cannot fetch Subreddit with id: "+id));
        return subredditMapper.mapSubredditToDto(subreddit);
    }



}
