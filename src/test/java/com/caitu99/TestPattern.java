package com.caitu99;

import com.caitu99.importdata.MainImport;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lion on 2015/12/7 0007.
 */
public class TestPattern {
    private static final Logger logger = LoggerFactory.getLogger(TestPattern.class);
    @Test
    public void test1()
    {
        InputStream inputStream = MainImport.class.getClassLoader().getResourceAsStream("log4j.properties");
        PropertyConfigurator.configure(inputStream);
        Pattern pattern = Pattern.compile("(.*?)(?=\\(\\d{3,4}\\))");
        String string = "民生银行(1234)";
        Matcher matcher = pattern.matcher(string);
        if(matcher.find()) {
            String group = matcher.group();
            System.out.println(group);
            logger.debug("group: {}", group);
        }

    }

    @Test
    public void test2() {
        Date date = new Date(1445097600000L);
        System.out.println(date.toLocaleString());

    }
}
