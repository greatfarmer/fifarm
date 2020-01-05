package com.fifarm.spider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PathController {

    @GetMapping("/search/{playerName}")
    public String searchByName(@PathVariable(value="playerName") String playerName, Model model) {
        model.addAttribute("playerName", playerName);
        return "playerList";
    }

    @GetMapping("/player/{playerId}")
    public String linkById(@PathVariable(value="playerId") String playerId, Model model) {
        model.addAttribute("playerId", playerId);
        return "player";
    }

}