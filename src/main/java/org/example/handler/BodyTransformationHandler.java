package org.example.handler;

import org.example.command.BodyTransformationCommand;
import org.example.model.Request;

public class BodyTransformationHandler extends Handler {

    @Override
    public void handle(Request request) {
        new BodyTransformationCommand().execute(request);

        if (nextHandler != null) {
            nextHandler.handle(request);
        }
    }
}
