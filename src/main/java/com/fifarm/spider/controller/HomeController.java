package com.fifarm.spider.controller;

import com.fifarm.spider.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    LogService logService;

    @GetMapping("/search/{playerName}")
    public String searchByName(@PathVariable(value="playerName") String playerName, Model model) {
        getClientIp();
        model.addAttribute("playerName", playerName);
        return "playerList";
    }

    @GetMapping("/player/{playerId}")
    public String linkById(@PathVariable(value="playerId") String playerId, Model model) {
        model.addAttribute("playerId", playerId);
        return "player";
    }

    private void getClientIp() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = req.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            ip = req.getRemoteAddr();
        }
        logService.info(String.format("[clientIp] %s", ip));
    }

}