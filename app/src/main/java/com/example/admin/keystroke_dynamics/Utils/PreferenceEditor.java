package com.example.admin.keystroke_dynamics.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.admin.keystroke_dynamics.Login.LoggedUser;

public class PreferenceEditor {

    public PreferenceEditor(Context context){
        this.context = context;
        loggerUserCredentials = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        id = loggerUserCredentials.getInt(KEY_ID, -1);
        email = loggerUserCredentials.getString(KEY_EMAIL, null);
        username = loggerUserCredentials.getString(KEY_USERNAME, null);
    }

    public void load(LoggedUser loggedUser){
        if(email != null && username != null) {
            loggedUser.setId(id);
            loggedUser.setEmail(email);
            loggedUser.setUsername(username);
        }
    }

    public void save(LoggedUser loggedUser){
        credentialsEditor = loggerUserCredentials.edit();
        credentialsEditor.putInt(KEY_ID, loggedUser.getId());
        credentialsEditor.putString(KEY_EMAIL, loggedUser.getEmail());
        credentialsEditor.putString(KEY_USERNAME, loggedUser.getUsername());
        credentialsEditor.apply();
    }

    public int getUserId(){
        return loggerUserCredentials.getInt(KEY_ID, -1);
    }

    public void clear(){
        loggerUserCredentials.edit().clear().commit();
    }

    public boolean isEmpty(){
        return email == null && username == null;
    }

    private Context context;
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String SP_NAME = "Credentials";
    private SharedPreferences loggerUserCredentials;
    private SharedPreferences.Editor credentialsEditor;
    private int id;
    private String email;
    private String username;
}
