package net.fifarm.spider.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonUtilsTest {

    @Test
    public void test() throws IOException {
        String jsonString = FileUtils.readFile("test/json/SeonghunKang.json");
        JsonObject jobj = JsonUtils.getJsonObjectFromString(jsonString);
        assertThat(jobj).isNotNull();

        JsonArray jarr = JsonUtils.getJsonArray(jobj, "items");
        assertThat(jarr).hasSize(1);

        jarr.forEach(item -> assertThat(JsonUtils.getString((JsonObject) item, "id")).isEqualTo("9107107107"));
    }

}