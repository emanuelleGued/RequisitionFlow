package org.example.command;

import org.example.model.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class BodyTransformationCommand implements Command {

    @Override
    public void execute(Request request) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> attributes = objectMapper.readValue(request.getBody(), Map.class);
            request.setAttributes(attributes);
            System.out.println("Corpo transformado com sucesso.");
        } catch (Exception e) {
            throw new IllegalArgumentException("Falha na transformação do corpo JSON: " + e.getMessage());
        }
    }

    public static class PermissionCommand implements Command {

        @Override
        public void execute(Request request) {
            if (!"admin".equals(request.getHeaders().get("Role"))) {
                throw new IllegalArgumentException("Permissão negada: Usuário sem permissões adequadas.");
            }

            System.out.println("Permissão concedida.");
        }
    }
}