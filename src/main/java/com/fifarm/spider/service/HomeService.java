package com.fifarm.spider.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fifarm.spider.cv.FifarmCV;
import com.fifarm.spider.dto.Club;
import com.fifarm.spider.dto.League;
import com.fifarm.spider.dto.Nation;
import com.fifarm.spider.dto.Player;
import com.fifarm.spider.net.HttpRequestService;
import com.fifarm.spider.net.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HomeService {

    @Autowired
    HttpRequestService httpRequestService;

    @Autowired
    ObjectMapper objectMapper;

    public Result getAPI() throws Exception {
        String url = String.format(FifarmCV.FUT_PLAYER_API_URL, "Heung Min Son");

        return httpRequestService.sendGet(url);
    }

    public Map<String, Object> jsonToMap() throws Exception {
        Result result = getAPI();
        String content = result.getResponse();

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(content, new TypeReference<Map<String,Object>>(){});
    }

    public Player jsonToPlayer(Result result) throws Exception {
        JsonNode item = getItems(result).get(0);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.treeToValue(item, Player.class);
    }

    public League jsonToLeague(Result result) throws Exception {
        JsonNode item = getItems(result).get(0);
        JsonNode league = item.get("league");

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.treeToValue(league, League.class);
    }

    public Nation jsonToNation(Result result) throws Exception {
        JsonNode item = getItems(result).get(0);
        JsonNode nation = item.get("nation");

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.treeToValue(nation, Nation.class);
    }

    public Club jsonToClub(Result result) throws Exception {
        JsonNode item = getItems(result).get(0);
        JsonNode club = item.get("club");

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.treeToValue(club, Club.class);
    }

    private ArrayNode getItems(Result result) throws Exception {
        JsonNode node = objectMapper.readTree(result.getResponse());
        return (ArrayNode) node.get("items");
    }

}
