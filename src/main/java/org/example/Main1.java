/*
    Code to demonstrate how to create Logger object and use it to log messages
*/

package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class Main1 {

    public static void main(String[] args) {
        // basic config file path
        String configFilePath = "C:\\log4j\\src\\main\\resources\\log4j2-basic.xml";
        // Configure Log4j2 with the specified configuration file
        // this way logger will be configured based on configuration provided in the specified config file
        // Here we are explicitly defining which config file to use
        Configurator.initialize("BasicConfiguration", configFilePath);
        // Create a logger for your class
        Logger logger =  LogManager.getLogger(Main1.class);
        // Log messages at various log levels
        logger.trace("This is a TRACE level message.");
        logger.debug("This is a DEBUG level message.");
        logger.info("This is an INFO level message.");
        logger.warn("This is a WARN level message.");
        logger.error("This is an ERROR level message.");
        logger.fatal("This is a FATAL level message.");
    }
}