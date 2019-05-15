package com.mina.mail.ru.cinema;

import javafx.application.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Mina on 15.05.2019.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ControllerTests {

    @Value("${server.port.react}")
    private int serverPort;

    @Value("${server.servlet.context-path}")
    private String contextPath;
    private String base;

    private WebDriver webDriver;

   @Before
   public void setup(){
       System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
       webDriver = new ChromeDriver();
       this.base = "http://localhost:" + serverPort;
   }


    @Test
    public void testACheckHomeUrl() throws Exception {
        webDriver.get(base);
    }
}
