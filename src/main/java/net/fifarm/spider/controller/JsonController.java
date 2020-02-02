package net.fifarm.spider.controller;

import net.fifarm.spider.service.MongoService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JsonController {

    @Autowired
    MongoService mongoService;

    @GetMapping("/search/{name}")
    public List<Document> getPlayerListByName(@PathVariable(value="name") String name) {
        return mongoService.searchByNameUsingTextScore(name, 100);
    }

    @GetMapping("/player/{id}")
    public Document getPlayerById(@PathVariable(value="id") String id) {
        return mongoService.searchById(id, 1).get(0);
    }

    @GetMapping("/autocomplete")
    public List<Document> getPlayerNames(@RequestParam("term") String term) {
        return mongoService.searchPlayerNames(term, 30);
    }

}

