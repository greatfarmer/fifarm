package com.fifarm.spider.controller;

import com.fifarm.spider.cv.FifarmCV;
import com.fifarm.spider.db.ClubDbHandler;
import com.fifarm.spider.db.LeagueDbHandler;
import com.fifarm.spider.db.NationDbHandler;
import com.fifarm.spider.db.PlayerDbHandler;
import com.fifarm.spider.dto.Club;
import com.fifarm.spider.dto.League;
import com.fifarm.spider.dto.Nation;
import com.fifarm.spider.dto.Player;
import com.fifarm.spider.net.HttpRequestService;
import com.fifarm.spider.net.Result;
import com.fifarm.spider.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    HomeService homeService;

    @Autowired
    HttpRequestService httpRequestService;

    @Autowired
    PlayerDbHandler playerDbHandler;

    @Autowired
    LeagueDbHandler leagueDbHandler;

    @Autowired
    NationDbHandler nationDbHandler;

    @Autowired
    ClubDbHandler clubDbHandler;

    @RequestMapping("/player")
    public String name(@RequestParam("name") String name, Model model) throws Exception {
        String url = String.format(FifarmCV.FUT_PLAYER_API_URL, name);
        Result result = httpRequestService.sendGet(url);
        Player player = getPlayer(result);
        model.addAttribute("player", player);
        return "player";
    }

    private Player getPlayer(Result result) throws Exception {
        League league = homeService.jsonToLeague(result);
        leagueDbHandler.addLeague(league);

        Nation nation = homeService.jsonToNation(result);
        nationDbHandler.addNation(nation);

        Club club = homeService.jsonToClub(result);
        clubDbHandler.addClub(club);

        Player player = homeService.jsonToPlayer(result);
        player.setLeague(league);
        player.setNation(nation);
        player.setClub(club);
        playerDbHandler.addPlayer(player);

        return player;
    }

}