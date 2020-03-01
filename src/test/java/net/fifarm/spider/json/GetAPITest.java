package net.fifarm.spider.json;

import net.fifarm.spider.net.HttpRequestService;
import net.fifarm.spider.net.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class GetAPITest {

    @Test
    public void test() throws Exception {
        String url = "https://www.easports.com/fifa/ultimate-team/api/fut/item?id=251858344";

        HttpRequestService httpRequestService = new HttpRequestService();
        Result result = httpRequestService.sendGet(url);
        assertThat(result.getResponseCode()).isEqualTo(200);
    }

}
