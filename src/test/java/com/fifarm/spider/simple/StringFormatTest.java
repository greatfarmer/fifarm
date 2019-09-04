package com.fifarm.spider.simple;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringFormatTest {

    @Test
    public void test() {
        String baseUrl = "https://www.easports.com/fifa/ultimate-team/api/fut/item?jsonParamObject=%%7B%%22page%%22:1,%%22baseid%%22:%d%%7D";
        String url = String.format(baseUrl, 176676);
        assertThat(url).isEqualTo("https://www.easports.com/fifa/ultimate-team/api/fut/item?jsonParamObject=%7B%22page%22:1,%22baseid%22:176676%7D");
    }

}
