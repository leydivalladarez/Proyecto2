package edu.espe.contable.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Aquí puedes manejar el mensaje recibido y enviar una respuesta
        String payload = message.getPayload();
        session.sendMessage(new TextMessage("Servidor recibió: " + payload));
    }
}

