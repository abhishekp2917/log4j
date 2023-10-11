/*
    Code to demonstrate log hierarchy and how to use separate loggers for different components of application
*/

package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class Main4 {

    public static void main(String[] args) {

        String configFilePath = "C:\\log4j\\src\\main\\resources\\log4j2-hierarchy.xml";

        Configurator.initialize("HierarchyConfiguration", configFilePath);

        // creating root logger for 'Main4' class
        Logger logger =  LogManager.getLogger(Main4.class);
        // creating 'Account' logger for logging message that is related to Account component (class)
        Logger accountLogger = LogManager.getLogger("org.example.Account");
        logger.info("Application started");
        Account account = new Account(102, 1234, 100000);
        accountLogger.info("Account created successfully");
        logger.info("Withdrawing amount 20000 from account Number : {}", account.accNumber);
        logger.trace("Current balance in account - {} : {}", account.accNumber, account.balance);
        account.withdrawAmount(20000, 1234);
        logger.trace("Balance after transaction in account - {} : {}", account.accNumber, account.balance);
        logger.info("Withdrawing amount 90000 from account Number : {}", account.accNumber);
        logger.trace("Current balance in account - {} : {}", account.accNumber, account.balance);
        account.withdrawAmount(90000, 1243);
        logger.trace("Balance after transaction in account - {} : {}", account.accNumber, account.balance);
        logger.info("Withdrawing amount 90000 from account Number : {}", account.accNumber);
        logger.trace("Current balance in account - {} : {}", account.accNumber, account.balance);
        account.withdrawAmount(90000, 1234);
        logger.trace("Balance after transaction in account - {} : {}", account.accNumber, account.balance);
    }
}

// Account class which has methods (components) for authentication and withdraw amount
class Account {

    public int accNumber;
    public int pinNumber;
    public double balance;

    // creating logger for authentication method (component)
    // we will use this logger to log message inside authentication method so that in case we want to change the severity
    // of logging of this component, we just need to change configuration of this logger
    private Logger accountAuthenticateLogger = LogManager.getLogger("org.example.Account.authenticate");

    // creating logger for withdrawAmount method (component)
    // this logger is dedicated to log message inside withdrawAmount method so that we will get fine-grained control
    // logging
    private Logger accountWithdrawAmountLogger = LogManager.getLogger("org.example.Account.withdrawAmount");

    public Account(int accNumber, int pinNumber, double balance) {
        this.accNumber = accNumber;
        this.pinNumber = pinNumber;
        this.balance = balance;
        // configuring config file for the logger
        Configurator.initialize("BasicConfiguration", "C:\\log4j\\src\\main\\resources\\log4j2-hierarchy.xml");
    }

    private boolean authenticate(int pinNumber) {
        // using 'accountAuthenticateLogger' to log message inside authenticate method at various level i.e. INFO,
        // TRACE, ERROR etc
        accountAuthenticateLogger.debug("Entered into authenticate method");
        accountAuthenticateLogger.trace("Parameters : pinNumber : {}", pinNumber);
        accountAuthenticateLogger.trace("pinNumber : {}", this.pinNumber);
        if(pinNumber==this.pinNumber) {
            accountAuthenticateLogger.info("Authenticated successfully");
            return true;
        }
        else {
            accountAuthenticateLogger.error("Authentication failed");
            return false;
        }
    }

    public boolean withdrawAmount(int amount, int pinNumber) {
        // using 'accountWithdrawAmountLogger' to log message inside withdrawAmount method at various level i.e. INFO,
        // TRACE, ERROR etc
        accountWithdrawAmountLogger.debug("Entered into WithdrawAmount method");
        accountWithdrawAmountLogger.trace("Parameters : Amount : {}, pinNumber : {}", amount, pinNumber);
        if(authenticate(pinNumber)) {
            accountWithdrawAmountLogger.trace("Current balance : {}", this.balance);
            if(amount>this.balance) {
                accountWithdrawAmountLogger.error("Insufficient balance");
                return false;
            }
            else {
                this.balance -= amount;
                accountWithdrawAmountLogger.trace("Balance left after transaction : {}", this.balance);
                accountWithdrawAmountLogger.info("Amount {} withdrawn successfully", amount);
                return true;
            }
        }
        else {
            accountWithdrawAmountLogger.error("Invalid pin number entered");
            return false;
        }
    }
}

