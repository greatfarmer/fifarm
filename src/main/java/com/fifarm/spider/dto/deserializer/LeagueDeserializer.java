package com.fifarm.spider.dto.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fifarm.spider.dto.League;

import java.io.IOException;

public class LeagueDeserializer extends StdDeserializer<League> {

    public LeagueDeserializer() {
        this(null);
    }

    public LeagueDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public League deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        League league = new League();

        league.setId(node.get("id").asLong());
        league.setName(node.get("name").asText());
        league.setAbbrName(node.get("abbrName").asText());
        league.setImgUrl(node.get("imgUrl").asText());

        JsonNode imageUrlsNode = node.get("imageUrls");

        league.setImageUrlsDark(imageUrlsNode.get("dark").asText());
        league.setImageUrlsLight(imageUrlsNode.get("light").asText());

        return league;
    }
}