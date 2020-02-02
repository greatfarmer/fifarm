package net.fifarm.spider.net;

import net.fifarm.spider.cv.FifarmCV;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class HttpRequestService {

    public Result sendGet(String targetUrl) throws Exception {
        Result result = new Result();

        URL url = new URL(targetUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", FifarmCV.USER_AGENT);

        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        result.setResponseCode(responseCode);
        result.setResponse(response.toString());

        return result;
    }

}
