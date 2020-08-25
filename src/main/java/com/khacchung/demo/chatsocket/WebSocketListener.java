package com.khacchung.demo.chatsocket;

import com.khacchung.demo.chatsocket.model.ChatMessageModel;
import com.khacchung.demo.chatsocket.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketListener {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketListener.class.getName());

    @Autowired
    private SimpMessageSendingOperations sendingOperations;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Nhận được 1 kết nối mới");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get(Constant.USERNAME);
        if (username != null) {
            logger.info("Người dùng thoát ngắt kết nối: " + username);

            ChatMessageModel chatMessageModel = new ChatMessageModel();
            chatMessageModel.setMessageType(ChatMessageModel.MessageType.LEAVE);
            chatMessageModel.setSender(username);

            sendingOperations.convertAndSend("/topic/public", chatMessageModel);
        }
    }
}
