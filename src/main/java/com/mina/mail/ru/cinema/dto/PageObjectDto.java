package com.mina.mail.ru.cinema.dto;


import lombok.Data;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Mina on 18.05.2019.
 */
@Data
public class PageObjectDto {

    @FindBy(css = ".film-button")
    private   WebElement filmTitle;

    @FindBy(css = ".block-warn")
    private   WebElement bubblingWarn;

    @FindBy(css = ".head-sign-in-button")
    private WebElement signInButton;

    @FindBy(css = ".head-sign-up-button")
    private WebElement signUpButton;

    @FindBy(css = ".head-login-item > input")
    private WebElement inputSignIn;

    @FindBy(css = ".user-not-found-error")
    private WebElement userNotFoundWarn;

    @FindBy(css = ".modal-sign-up-button")
    private WebElement registerButton;

    @FindBy(css = ".modal-input-block > input")
    private WebElement inputSignUp;

    @FindBy(css = ".sign-up-title")
    private WebElement signUpModalTitle;

    @FindBy(css = ".modal-cancel-button")
    private WebElement cancelButton;

    @FindBy(css = ".user-sign-in")
    private WebElement signInWarn;

    @FindBy(css = ".hall-block")
    private WebElement cinemaHallBlock;

    public PageObjectDto(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void signUp(CharSequence ch) {
        inputSignUp.sendKeys(ch);
        registerButton.click();
    }

    public void signIn(CharSequence ch) {
        inputSignIn.sendKeys(ch);
        signInButton.click();
    }

    public void clearInputField(WebElement el) {
        el.sendKeys(Keys.CONTROL + "a");
        el.sendKeys(Keys.DELETE);
    }
}
