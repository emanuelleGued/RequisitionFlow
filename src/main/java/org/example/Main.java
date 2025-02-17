package org.example;

import org.example.handler.AuthenticationHandler;
import org.example.handler.BodyTransformationHandler;
import org.example.handler.PermissionHandler;
import org.example.model.Request;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Request request = new Request();
        request.setHeaders(new HashMap<>());
        request.getHeaders().put("Authorization", "Bearer token_valid");
        request.getHeaders().put("Role", "admin");
        request.setBody("{\"key\": \"value\"}");

        AuthenticationHandler authenticationHandler = new AuthenticationHandler();
        PermissionHandler permissionHandler = new PermissionHandler();
        BodyTransformationHandler bodyTransformationHandler = new BodyTransformationHandler();

        authenticationHandler.setNextHandler(permissionHandler);
        permissionHandler.setNextHandler(bodyTransformationHandler);

        authenticationHandler.handle(request);

        System.out.println("Atributos da requisição: " + request.getAttributes());
    }
}
