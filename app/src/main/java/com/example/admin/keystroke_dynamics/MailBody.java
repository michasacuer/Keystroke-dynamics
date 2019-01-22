package com.example.admin.keystroke_dynamics;

import android.content.res.Resources;

public class MailBody {

    public MailBody(String... body){
        this.body = this.welcome + "\nUsername: " + body[0] + "\nEmail: " + body[1] + "\nPassword: " + body[2];
    }

    public String getBody() { return this.body; }

    private String body;
    private String welcome = Resources.getSystem().getString(R.string.welcome);
}
