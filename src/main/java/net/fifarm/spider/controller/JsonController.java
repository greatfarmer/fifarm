package net.fifarm.spider.controller;

import net.fifarm.spider.cv.FifarmCV;
import net.fifarm.spider.net.HttpRequestService;
import net.fifarm.spider.net.Result;
import net.fifarm.spider.service.MongoService;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class JsonController {

    @Autowired
    HttpRequestService httpRequestService;

    @Autowired
    MongoService mongoService;

    @GetMapping("/search/{name}")
    public List<DBObject> getPlayerListByName(@PathVariable(value="name") String name) throws Exception {
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

    @GetMapping("/player/{id}")
    public DBObject getPlayerById(@PathVariable(value="id") String id) {
        DBCursor cursorDoc = mongoService.searchById(id, 1);

        DBObject player = null;
        while (cursorDoc.hasNext()) {
            player = cursorDoc.next();
            break;
        }

        return player;
    }

    @GetMapping("/autocomplete")
    public List<DBObject> getPlayerNames(@RequestParam("term") String term) {
        DBCursor cursorDoc = mongoService.searchPlayerNames(term, 30);

        List<DBObject> playerNames = new ArrayList<>();
        while (cursorDoc.hasNext()) {
            playerNames.add(cursorDoc.next());
        }

        return playerNames;
    }

}
