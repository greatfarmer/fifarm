package com.fifarm.spider.util;

import com.fifarm.spider.cv.FifarmCV;
import com.fifarm.spider.net.HttpRequestService;
import com.fifarm.spider.net.Result;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonUtilsTest {

    @Test
    public void test() {
        JsonObject jobj = JsonUtils.getJsonObjectFromString(getContent("pele"));
        assertThat(jobj).isNotNull();

        JsonArray jarr = JsonUtils.getJsonArray(jobj, "items");
        assertThat(jarr).hasSize(13);

        jarr.forEach(item -> System.out.println(JsonUtils.getString((JsonObject) item, "id")));
    }

    private String getContent(String name) {
        String url = String.format(FifarmCV.FUT_PLAYER_API_URL, name);
        HttpRequestService httpRequestService = new HttpRequestService();
        try {
            Result result = httpRequestService.sendGet(url);
            if (result.getResponseCode() == 200) {
                return result.getResponse();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}