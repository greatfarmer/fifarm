package com.fifarm.spider.controller;

import com.fifarm.spider.cv.FifarmCV;
import com.fifarm.spider.net.HttpRequestService;
import com.fifarm.spider.net.Result;
import com.fifarm.spider.service.MongoService;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/json")
public class JsonController {

    @Autowired
    HttpRequestService httpRequestService;

    @Autowired
    MongoService mongoService;

    @RequestMapping(method = RequestMethod.GET, value = "/search/{name}")
    public @ResponseBody List<DBObject> getPlayerListByName(@PathVariable(value="name") String name) throws Exception {
        List<DBObject> players = new ArrayList<>();
        String url = String.format(FifarmCV.FUT_PLAYER_API_URL, name);
        Result result = httpRequestService.sendGet(url);
        if (result.getResponseCode() == 200 && mongoService.insertToMongo(result.getResponse())) {
            DBCursor cursorDoc = mongoService.searchByNameUsingTextScore(name, 100);
            while (cursorDoc.hasNext()) {
                players.add(cursorDoc.next());
            }
        }

        return players;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/player/{id}")
    public @ResponseBody DBObject getPlayerById(@PathVariable(value="id") String id) {
        DBCursor cursorDoc = mongoService.searchById(id, 1);

        DBObject player = null;
        while (cursorDoc.hasNext()) {
            player = cursorDoc.next();
            break;
        }

        return player;
    }

}

