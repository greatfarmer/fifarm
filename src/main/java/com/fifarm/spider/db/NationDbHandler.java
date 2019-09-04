package com.fifarm.spider.db;

import com.fifarm.spider.dto.Nation;
import com.fifarm.spider.repository.NationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NationDbHandler {

    @Autowired
    NationRepository nationRepository;

    public void addNation(Nation nation) {
        nationRepository.save(nation);
    }
}
