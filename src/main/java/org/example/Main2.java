/*
    Code to demonstrate how to log message to a file and have a rotation policy for it
*/

package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class Main2 {

    public static void main(String[] args) throws InterruptedException {
        // config file path which has configuration of file rotation Appender
        String configFilePath = "C:\\log4j\\src\\main\\resources\\log4j2-file_rotation.xml";
        Configurator.initialize("SizeBasedConfiguration", configFilePath);
        // creating logger object
        Logger logger = LogManager.getLogger(Main1.class);
        // logging message 1000 times so that log file size will exceed its limit and size based Appender
        // will be triggered and will roll out new log file
        for(int i=0; i<1000; i++) {
            logger.trace(i + " This is a TRACE level message.");
            logger.debug(i + " This is a DEBUG level message.");
            logger.info(i + " This is an INFO level message.");
            logger.warn(i + " This is a WARN level message.");
            logger.error(i + " This is an ERROR level message.");
            logger.fatal(i + " This is a FATAL level message.");
        }
        // making the application sleep for 1 second so that time based Appender will roll out new log file
        // as time based Appender configured to roll out new file every 1 second
        Thread.sleep(1000);
        // logging message again once system comes out of the sleep due to which time based Appender will roll out new
        // log file since 1 second has passed
        for(int i=1000; i<2000; i++) {
            logger.trace(i + " This is a TRACE level message.");
            logger.debug(i + " This is a DEBUG level message.");
            logger.info(i + " This is an INFO level message.");
            logger.warn(i + " This is a WARN level message.");
            logger.error(i + " This is an ERROR level message.");
            logger.fatal(i + " This is a FATAL level message.");
        }
    }
}