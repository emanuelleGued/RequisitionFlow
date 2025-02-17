package org.example.handler;

import org.example.command.PermissionCommand;
import org.example.model.Request;

public class PermissionHandler extends Handler {

    @Override
    public void handle(Request request) {
        new PermissionCommand().execute(request);

        if (nextHandler != null) {
            nextHandler.handle(request);
        }
    }
}
