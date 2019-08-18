package com.fifarm.spider.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fifarm.spider.dto.Player;
import com.fifarm.spider.net.Result;
import com.fifarm.spider.service.HomeService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonToObjectTest {

    @Test
    public void jsonToPlayer() throws Exception {
        HomeService homeService = new HomeService();
        Result result = homeService.getAPI();
        String content = result.getResponse();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(content);
        ArrayNode items = (ArrayNode) node.get("items");
        JsonNode item = items.get(0);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Player player = objectMapper.treeToValue(item, Player.class);

        System.out.println(player);
        assertThat(player.getFirstName()).isEqualTo("Heung Min");
        assertThat(player.getLastName()).isEqualTo("Son");
        assertThat(player.getPosition()).isEqualTo("ST");
        assertThat(player.getAge()).isEqualTo(27);
    }

}
