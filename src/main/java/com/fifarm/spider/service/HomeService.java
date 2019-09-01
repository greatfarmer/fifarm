package com.fifarm.spider.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fifarm.spider.dto.League;
import com.fifarm.spider.dto.Player;
import com.fifarm.spider.net.HttpRequestService;
import com.fifarm.spider.net.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HomeService {

    @Autowired
    ObjectMapper objectMapper;

    public Result getAPI() throws Exception {
        String url = "https://www.easports.com/fifa/ultimate-team/api/fut/item?jsonParamObject=%7B%22baseid%22:%22200104%22,%22link%22:1%7D";
        HttpRequestService httpRequestService = new HttpRequestService();
        return httpRequestService.sendGet(url);
    }

    public Map<String, Object> jsonToMap() throws Exception {
        Result result = getAPI();
        String content = result.getResponse();

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(content, new TypeReference<Map<String,Object>>(){});
    }

    public Player jsonToPlayer() throws Exception {
        JsonNode item = getItems().get(0);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.treeToValue(item, Player.class);
    }

    public League jsonToLeague() throws Exception {
        JsonNode item = getItems().get(0);
        JsonNode league = item.get("league");

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.treeToValue(league, League.class);
    }

    private ArrayNode getItems() throws Exception {
        JsonNode node = objectMapper.readTree(getContent());
        return (ArrayNode) node.get("items");
    }

    private String getContent() throws Exception {
        return getAPI().getResponse();
    }

}
