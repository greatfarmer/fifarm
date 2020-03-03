package net.fifarm.spider.util;

import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class FileUtilsTest {

    @Test
    public void readFile() throws IOException {
        String jsonString = FileUtils.readFile("test/json/SeonghunKang.json");
        assertThat(jsonString).isNotEmpty();
        assertThat(jsonString).contains("Seonghun Kang");
    }

}