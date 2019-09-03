package com.fifarm.spider.dto.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fifarm.spider.dto.Club;
import com.fifarm.spider.dto.League;

import java.io.IOException;

public class ClubDeserializer extends StdDeserializer<Club> {

    public ClubDeserializer() {
        this(null);
    }

    public ClubDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Club deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Club club = new Club();

        club.setId(node.get("id").asLong());
        club.setName(node.get("name").asText());
        club.setAbbrName(node.get("abbrName").asText());
        club.setImgUrl(node.get("imgUrl").asText());

        JsonNode imageUrlsNode = node.get("imageUrls");

        club.setImageUrlsDark(imageUrlsNode.get("dark").get("medium").asText());
        club.setImageUrlsLight(imageUrlsNode.get("light").get("medium").asText());

        return club;
    }
}