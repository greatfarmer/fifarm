package com.fifarm.spider.controller;

import com.fifarm.spider.cv.FifarmCV;
import com.fifarm.spider.net.HttpRequestService;
import com.fifarm.spider.net.Result;
import com.fifarm.spider.service.LogService;
import com.fifarm.spider.service.MongoService;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    HttpRequestService httpRequestService;

    @Autowired
    MongoService mongoService;

    @Autowired
    LogService logService;

    @RequestMapping("/player")
    public String findByName(@RequestParam("name") String name, Model model) throws Exception {
        getClientIp();

        List<DBObject> players = new ArrayList<>();
        String url = String.format(FifarmCV.FUT_PLAYER_API_URL, name);
        Result result = httpRequestService.sendGet(url);
        if (result.getResponseCode() == 200 && mongoService.insertToMongo(result.getResponse())) {
            DBCursor cursorDoc = mongoService.searchByNameUsingTextScore(name, 100);
            while (cursorDoc.hasNext()) {
                players.add(cursorDoc.next());
            }
        }

        model.addAttribute("players", players);
        return "playerList";
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