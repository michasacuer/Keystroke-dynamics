package com.example.admin.keystroke_dynamics.Login;

public class LoggedUser {

    public static LoggedUser getInstance(){
        if(INSTANCE == null)
            INSTANCE = new LoggedUser();
        return INSTANCE;
    }

    public void resetInstance(){
        INSTANCE.setId(ID_EMPTY);
        INSTANCE.setUsername(STRING_EMPTY);
        INSTANCE.setEmail(STRING_EMPTY);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    private LoggedUser(){
        id = ID_EMPTY;
        username = STRING_EMPTY;
        email = STRING_EMPTY;
    }

    private static int id;
    private static String username;
    private static String email;
    private static LoggedUser INSTANCE;
    private static final String STRING_EMPTY = "";
    private static final int ID_EMPTY = -1;

}
