package org.example.command;

import org.example.model.Request;

public class PermissionCommand implements Command {

    @Override
    public void execute(Request request) {
        if (!"admin".equals(request.getHeaders().get("Role"))) {
            throw new IllegalArgumentException("Permissão negada: Usuário sem permissões adequadas.");
        }

        System.out.println("Permissão concedida.");
    }
}
