package com.mina.mail.ru.cinema;

import com.mina.mail.ru.cinema.dto.PageObjectDto;
import com.mina.mail.ru.cinema.service.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by Mina on 15.05.2019.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControllerTests {

    private static final String mainPageUrl = "http://localhost:3000";
    private static final String testUser = "USER_TEST";
    private PageObjectDto page = initPage();
    private static WebDriver webDriver;

    @Autowired
    private UserService userService;

    private PageObjectDto initPage() {
        if (page == null) {
            return new PageObjectDto(webDriver);
        } else return page;
    }

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get(mainPageUrl);
    }

    @AfterClass
    public static void close() {
       webDriver.quit();
    }

    @Test
    public void testAUnauthorized() throws InterruptedException {
        page.getFilmTitle().click();
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        Assert.assertTrue("Error occur when checking unauthorized status",
                page.getBubblingWarn().getText().equals("Unauthorized!"));

    }

    @Test
    public void testBInvalidUserSignIn() throws InterruptedException {
        page.signIn(testUser);
        Thread.sleep(500);
        Assert.assertTrue("Error occur when sing in with invalid user!",
                page.getUserNotFoundWarn().getText().equals("User not found!"));
        page.clearInputField(page.getInputSignIn());
    }

    @Test
    public void testCOpenSignUpModal() {
        page.getSignUpButton().click();
        Assert.assertTrue("Error occur when opening sign up modal!",
                page.getSignUpModalTitle().getText().equals("Registration"));
    }

    @Test
    public void testDSaveUser() throws InterruptedException {
        Thread.sleep(1000);
        page.signUp(testUser);
        Assert.assertTrue("Error occur when saving user",
                page.getBubblingWarn().getText().equals("User was created successfully!"));
        page.clearInputField(page.getInputSignUp());
    }

    @Test
    public void testEUserAlreadyExists() throws InterruptedException {
        Thread.sleep(1000);
        page.getSignUpButton().click();
        page.signUp(testUser);
        Assert.assertTrue("Error occur when checking that user already exists",
        page.getBubblingWarn().getText().equals("User already exists!"));
    }

    @Test
    public void testFUserSignIn() throws InterruptedException{
        page.getCancelButton().click();
        Thread.sleep(500);
        page.signIn(testUser);
        Assert.assertTrue("Error occur when user sign in",
                page.getSignInWarn().getText().contains("You are welcome"));
    }

    @Test
    public void  testGCheckFilm() throws InterruptedException {
        page.getFilmTitle().click();
        Thread.sleep(500);
        Assert.assertTrue("Error occur when displaying film seats",
                page.getCinemaHallBlock().isDisplayed());
        userService.deleteUser(testUser);
    }
}
