package com.fifarm.spider.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fifarm.spider.dto.Player;
import com.fifarm.spider.net.HttpRequestService;
import com.fifarm.spider.net.Result;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HomeService {

    public Result getAPI() throws Exception {
        String url = "https://www.easports.com/fifa/ultimate-team/api/fut/item?jsonParamObject=%7B%22baseid%22:%22200104%22,%22link%22:1%7D";
        HttpRequestService httpRequestService = new HttpRequestService();
        return httpRequestService.sendGet(url);
    }

    public Map<String, Object> jsonToMap() throws Exception {
        HomeService homeService = new HomeService();
        Result result = homeService.getAPI();
        String content = result.getResponse();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(content, new TypeReference<Map<String,Object>>(){});

        return map;
    }

    public Player jsonToPlayer() throws Exception {
        HomeService homeService = new HomeService();
        Result result = homeService.getAPI();
        String content = result.getResponse();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(content);
        ArrayNode items = (ArrayNode) node.get("items");
        JsonNode item = items.get(0);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Player player = objectMapper.treeToValue(item, Player.class);

        return player;
    }

}
