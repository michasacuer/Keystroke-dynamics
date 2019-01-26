package com.example.admin.keystroke_dynamics.Login;

public class LoggedUser {

    public static LoggedUser getInstance(){
        if(INSTANCE == null)
            INSTANCE = new LoggedUser();
        return INSTANCE;
    }

    public void resetInstance(){
        INSTANCE.setUsername(STRING_EMPTY);
        INSTANCE.setEmail(STRING_EMPTY);
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    private LoggedUser(){ }

    private static String username;
    private static String email;
    private static LoggedUser INSTANCE;
    private static final String STRING_EMPTY = "";
}
