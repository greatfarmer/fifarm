package net.fifarm.spider.json;

import net.fifarm.spider.net.HttpRequestService;
import net.fifarm.spider.net.Result;
import org.junit.Test;

public class GetAPITest {

    @Test
    public void test() throws Exception {
        String url = "https://www.easports.com/fifa/ultimate-team/api/fut/item?jsonParamObject=%7B%22baseid%22:%22200104%22,%22link%22:1%7D";

        HttpRequestService httpRequestService = new HttpRequestService();
        Result result = httpRequestService.sendGet(url);
        System.out.println(result);
    }

}
