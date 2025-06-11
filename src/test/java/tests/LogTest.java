package tests;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

@Log4j2
public class LogTest {

    private static final Logger log = LogManager.getLogger(LogTest.class);

    @Test
    public void test() {
        log.fatal("fatal");
        log.error("error");
        log.warn("warning");
        log.info("info");
        log.debug("debug");
        log.trace("trace");
    }
}
