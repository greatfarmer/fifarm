package com.fifarm.spider.dto.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fifarm.spider.dto.League;
import com.fifarm.spider.dto.Nation;

import java.io.IOException;

public class NationDeserializer extends StdDeserializer<Nation> {

    public NationDeserializer() {
        this(null);
    }

    public NationDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Nation deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Nation nation = new Nation();

        nation.setId(node.get("id").asLong());
        nation.setName(node.get("name").asText());
        nation.setAbbrName(node.get("abbrName").asText());
        nation.setImgUrl(node.get("imgUrl").asText());

        JsonNode imageUrlsNode = node.get("imageUrls");

        nation.setImageUrlsSmall(imageUrlsNode.get("small").asText());
        nation.setImageUrlsMedium(imageUrlsNode.get("medium").asText());
        nation.setImageUrlsLarge(imageUrlsNode.get("large").asText());

        return nation;
    }
}