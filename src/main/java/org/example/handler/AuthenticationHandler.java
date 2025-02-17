package org.example.handler;

import org.example.command.AuthenticationCommand;
import org.example.model.Request;

public class AuthenticationHandler extends Handler {

    @Override
    public void handle(Request request) {
        new AuthenticationCommand().execute(request);

        if (nextHandler != null) {
            nextHandler.handle(request);
        }
    }
}
