package com.fifarm.spider.db;

import com.fifarm.spider.dto.League;
import com.fifarm.spider.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LeagueDbHandler {

    @Autowired
    LeagueRepository leagueRepository;

    public void addLeague(League league) {
        leagueRepository.save(league);
    }
}
