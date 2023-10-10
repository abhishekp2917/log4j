/*
    Code to demonstrate how to log messages in various layouts
*/

package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class Main3 {

    public static void main(String[] args) {

        String configFilePath = "C:\\log4j\\src\\main\\resources\\log4j2-layouts.xml";

        Configurator.initialize("BasicConfiguration", configFilePath);

        // Create a logger for your class
        Logger logger =  LogManager.getLogger(Main3.class);
        // Log messages at various log levels
        logger.trace("This is a TRACE level message.");
        logger.debug("This is a DEBUG level message.");
        logger.info("This is an INFO level message.");
        logger.warn("This is a WARN level message.");
        logger.error("This is an ERROR level message.");
        logger.fatal("This is a FATAL level message.");
    }
}