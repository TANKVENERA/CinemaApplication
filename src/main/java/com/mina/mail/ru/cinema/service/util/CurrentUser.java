package com.mina.mail.ru.cinema.service.util;

/**
 * Created by Mina on 09.05.2019.
 */
public class CurrentUser {

    private String loggedInUser;
    boolean authenticated;

    public CurrentUser(String loggedInUser, boolean authenticated) {
        this.loggedInUser = loggedInUser;
        this.authenticated = authenticated;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
