package prog4.project1.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLogger {
    private static final Logger logger = LoggerFactory.getLogger(Logger.class);

    public static void logInfo(String message) {
        logger.info(message);
    }
}
