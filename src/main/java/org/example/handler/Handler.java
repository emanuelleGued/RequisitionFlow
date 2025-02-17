package org.example.handler;

import org.example.command.Command;
import org.example.model.Request;

public abstract class Handler {

    protected Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handle(Request request);
}
