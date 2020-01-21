package net.fifarm.spider.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtils {

    public static JsonObject getJsonObjectFromString(String jsonString) {
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(jsonString);
        return element.getAsJsonObject();
    }

    public static JsonArray getJsonArray(JsonObject jsonObject, String key) {
        return jsonObject.get(key).getAsJsonArray();
    }

    public static String getString(JsonObject jsonObject, String key) {
        return jsonObject.get(key).getAsString();
    }

}
