package org.example.command;

import org.example.model.Request;

public class AuthenticationCommand implements Command {

    @Override
    public void execute(Request request) {
        if (request.getHeaders().get("Authorization") == null) {
            throw new IllegalArgumentException("Autenticação falhou: Cabeçalho de autorização ausente.");
        }

        String token = request.getHeaders().get("Authorization");
        if (!"Bearer token_valid".equals(token)) {
            throw new IllegalArgumentException("Autenticação falhou: Token inválido.");
        }

        System.out.println("Autenticação bem-sucedida.");
    }
}
