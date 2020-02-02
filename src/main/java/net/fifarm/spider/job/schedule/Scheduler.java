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

@Component
public class Scheduler {

    @Autowired
    HttpRequestService httpRequestService;

    @Autowired
    MongoService mongoService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "0 0 1 3 * ?")
    public void getFutItemsFromFifaApi() throws Exception {
        boolean checkTotalPages = false;
        int totalPages = 1;
        int curPage = 1;

        logger.info("GET FUT ITEMS FROM FIFA API -> START");

        while (curPage <= totalPages) {
            String url = String.format(FifarmCV.FUT_PLAYER_API_URL_BY_PAGE, curPage);
            Result result = httpRequestService.sendGet(url);
            if (result.getResponseCode() == 200) {
                String jsonString = result.getResponse();
                mongoService.insertToMongo(jsonString);

                if (!checkTotalPages) {
                    JsonObject jsonObject = JsonUtils.getJsonObjectFromString(jsonString);
                    totalPages = Integer.parseInt(JsonUtils.getString(jsonObject, "totalPages"));
                    checkTotalPages = true;
                }
            }

            logger.info("GET FUT ITEMS FROM FIFA API -> {}/{}", curPage, totalPages);
            curPage++;

            Thread.sleep(10000); // delay 10 seconds
        }

        logger.info("GET FUT ITEMS FROM FIFA API -> DONE");
    }

}