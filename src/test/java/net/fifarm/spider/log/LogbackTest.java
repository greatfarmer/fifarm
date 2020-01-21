package net.fifarm.spider.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackTest {

    static final Logger logger = LoggerFactory.getLogger(LogbackTest.class);

    @Test
    public void test() {
        logger.debug("ID : {}", "foo");
    }
}
