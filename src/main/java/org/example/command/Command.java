package org.example.command;

import org.example.model.Request;

public interface Command {
    void execute(Request request);
}
