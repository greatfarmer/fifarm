package com.fifarm.spider.dto.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fifarm.spider.dto.Player;

import java.io.IOException;

public class PlayerDeserializer extends StdDeserializer<Player> {

    public PlayerDeserializer() {
        this(null);
    }

    public PlayerDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Player deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Player player = new Player();
        player.setFirstName(node.get("firstName").asText());
        player.setLastName(node.get("lastName").asText());
        player.setPosition(node.get("position").asText());

        player.setHeight(node.get("height").asInt());
        player.setWeight(node.get("weight").asInt());
        player.setBirthdate(node.get("birthdate").asText());
        player.setAge(node.get("age").asInt());

        player.setHeadshotImgUrl(node.get("headshot").get("imgUrl").asText());

        return player;
    }
}