package com.fifarm.spider.controller;

import com.fifarm.spider.db.PlayerDbHandler;
import com.fifarm.spider.dto.Player;
import com.fifarm.spider.net.Result;
import com.fifarm.spider.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    HomeService homeService;

    @Autowired
    PlayerDbHandler playerDbHandler;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("name", "seonghun");
        return "home";
    }

    @GetMapping("/api")
    public String api(Model model) throws Exception {
        Result result = homeService.getAPI();
        model.addAttribute("result", result);
        return "api";
    }

    @GetMapping("/map")
    public String map(Model model) throws Exception {
        Map<String, Object> map = homeService.jsonToMap();
        model.addAttribute("map", map);
        return "map";
    }

    @GetMapping("/player")
    public String player(Model model) throws Exception {
        Player player = homeService.jsonToPlayer();
        playerDbHandler.addPlayer(player);
        model.addAttribute("player", player);
        return "player";
    }

}