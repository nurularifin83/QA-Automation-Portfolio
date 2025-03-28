package com.orangehrm.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties prop;

    public ConfigReader(){
        prop = new Properties();

        try{
            FileInputStream fis = new FileInputStream("src/test/resources/configurations/config.properties");
            prop.load(fis);
        } catch (IOException io){
            io.printStackTrace();
        }
    }

    public String getUrl(){
        return prop.getProperty("PRODURL");
    }

    public String getBrowser() {
        return prop.getProperty("BROWSER");
    }

    public Long getGlobalWaitValue(){
        return (Long) Long.parseLong(prop.getProperty("GLOBALWAIT"));
    }

    public String getFieldsVerificationExcelName() {
        return prop.getProperty("FIELDS_VERIFICATION_EXCEL");
    }

    public String getMaxRetries(){
        return prop.getProperty("MAX_RETRIES");
    }

    public String getServer(){
        return prop.getProperty("ENV");
    }

    public String getParallelMode(){
        return prop.getProperty("PARALLEL_MODE");
    }

    public String getMaxParallelTests(){
        return prop.getProperty("MAX_PARALLEL_TESTS");
    }
}