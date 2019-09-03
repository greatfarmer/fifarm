package com.fifarm.spider.db;

import com.fifarm.spider.dto.Club;
import com.fifarm.spider.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClubDbHandler {

    @Autowired
    ClubRepository clubRepository;

    public void addClub(Club club) {
        clubRepository.save(club);
    }
}
