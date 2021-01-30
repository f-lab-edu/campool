package com.campool.auth;

import com.campool.model.UserSignUp;
import javax.servlet.http.HttpSession;

public class SessionAuth {

    public static final String AUTH_USER_KEY = "userSignUp";

    public static void setAuthenticatedUser(UserSignUp userSignUp, HttpSession session) {
        session.setAttribute(AUTH_USER_KEY, userSignUp);
    }

}
