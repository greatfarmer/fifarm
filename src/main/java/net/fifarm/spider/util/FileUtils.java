package net.fifarm.spider.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileUtils {

    public static String readFile(String path) throws IOException {
        ClassPathResource cpr = new ClassPathResource(path);
        byte[] byteArray = FileCopyUtils.copyToByteArray(cpr.getInputStream());
        return new String(byteArray, StandardCharsets.UTF_8);
    }

}
