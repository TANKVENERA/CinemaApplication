package com.mina.mail.ru.cinema;

import com.mina.mail.ru.cinema.repository.impl.UserDAO;
import com.mina.mail.ru.cinema.service.impl.UserService;
import org.junit.runner.RunWith;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mina on 18.05.2019.
 */

public class PageObject {

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

    public PageObject(WebDriver driver) {
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

    public WebElement getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(WebElement filmTitle) {
        this.filmTitle = filmTitle;
    }

    public WebElement getBubblingWarn() {
        return bubblingWarn;
    }

    public void setBubblingWarn(WebElement bubblingWarn) {
        this.bubblingWarn = bubblingWarn;
    }

    public WebElement getSignInButton() {
        return signInButton;
    }

    public void setSignInButton(WebElement signInButton) {
        this.signInButton = signInButton;
    }

    public WebElement getInputSignIn() {
        return inputSignIn;
    }

    public void setInputSignIn(WebElement inputSignIn) {
        this.inputSignIn = inputSignIn;
    }

    public WebElement getUserNotFoundWarn() {
        return userNotFoundWarn;
    }

    public void setUserNotFoundWarn(WebElement userNotFoundWarn) {
        this.userNotFoundWarn = userNotFoundWarn;
    }

    public WebElement getSignUpButton() {
        return signUpButton;
    }

    public void setSignUpButton(WebElement signUpButton) {
        this.signUpButton = signUpButton;
    }

    public WebElement getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(WebElement registerButton) {
        this.registerButton = registerButton;
    }

    public WebElement getInputSignUp() {
        return inputSignUp;
    }

    public void setInputSignUp(WebElement inputSignUp) {
        this.inputSignUp = inputSignUp;
    }

    public WebElement getSignUpModalTitle() {
        return signUpModalTitle;
    }

    public void setSignUpModalTitle(WebElement signUpModalTitle) {
        this.signUpModalTitle = signUpModalTitle;
    }

    public WebElement getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(WebElement cancelButton) {
        this.cancelButton = cancelButton;
    }

    public WebElement getSignInWarn() {
        return signInWarn;
    }

    public void setSignInWarn(WebElement signInWarn) {
        this.signInWarn = signInWarn;
    }

    public WebElement getCinemaHallBlock() {
        return cinemaHallBlock;
    }

    public void setCinemaHallBlock(WebElement cinemaHallBlock) {
        this.cinemaHallBlock = cinemaHallBlock;
    }

}
