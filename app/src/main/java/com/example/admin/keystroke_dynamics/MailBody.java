package com.example.admin.keystroke_dynamics;

import android.content.Context;

public class MailBody {

    public MailBody(Context context, String... body){
        this.body = context.getString(R.string.welcome) + "\nUsername: " + body[0] + "\nEmail: " + body[1] + "\nPassword: " + body[2];
    }

    public String getBody() { return this.body; }

    private String body;
}
