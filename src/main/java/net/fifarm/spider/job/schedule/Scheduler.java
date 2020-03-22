package net.fifarm.spider.job.schedule;

import com.google.gson.JsonObject;
import net.fifarm.spider.cv.FifarmCV;
import net.fifarm.spider.net.HttpRequestService;
import net.fifarm.spider.net.Result;
import net.fifarm.spider.service.MongoService;
import net.fifarm.spider.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

@Component
public class Scheduler {

    @Autowired
    HttpRequestService httpRequestService;

    @Autowired
    MongoService mongoService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "0 0 1 3 * ?")
    public void getFutItemsFromFifaApi() throws Exception {
        int curPage = 1;
        String jsonString = requestDataByPage(curPage);
        int totalPages = StringUtils.isEmpty(jsonString) ? -1 : getTotalPage(jsonString);
        if (totalPages == -1) {
            logger.info("GET FUT ITEMS FROM FIFA API IS FAILED");
            return;
        }

        logger.info("GET FUT ITEMS FROM FIFA API -> START");

        while (curPage <= totalPages) {
            logger.info("GET FUT ITEMS FROM FIFA API -> {}/{}", curPage, totalPages);
            mongoService.insertToMongo(requestDataByPage(curPage++));
            Thread.sleep(10000); // delay 10 seconds
        }

        logger.info("GET FUT ITEMS FROM FIFA API -> DONE");
    }

    private String requestDataByPage(int page) throws Exception {
        String url = String.format(FifarmCV.FUT_PLAYER_API_URL_BY_PAGE, page);
        Result result = httpRequestService.sendGet(url);
        if (result.getResponseCode() == 200) {
            return result.getResponse();
        }
        return "";
    }

    private int getTotalPage(String jsonString) {
        JsonObject jsonObject = JsonUtils.getJsonObjectFromString(jsonString);
        return Integer.parseInt(JsonUtils.getString(jsonObject, "totalPages"));
    }

}