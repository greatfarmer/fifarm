package net.fifarm.spider.simple;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ReadFileTest {

    @Test
    public void readFile() throws IOException {
        ClassPathResource cpr = new ClassPathResource("test/json/SeonghunKang.json");
        byte[] byteArray = FileCopyUtils.copyToByteArray(cpr.getInputStream());
        String jsonTxt = new String(byteArray, StandardCharsets.UTF_8);
        System.out.println(jsonTxt);
    }

}
