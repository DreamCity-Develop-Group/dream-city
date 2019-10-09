package com.dream.city.server;

import org.springframework.web.socket.*;

import javax.websocket.MessageHandler;
import java.io.IOException;

import static com.dream.city.server.SocketMessageHandler.MessageHandler.handleTextMessage;
import static com.dream.city.server.SocketMessageHandler.MessageHandler.handlePingMessage;
import static com.dream.city.server.SocketMessageHandler.MessageHandler.handlePongMessage;
import static com.dream.city.server.SocketMessageHandler.MessageHandler.handleBinaryMessage;

/**
 * @author Wvv
 * @program: dream-city
 * @description: SocketMessageHandler
 * @create: 2019/10/2019/10/9 12:38:30 [星期三]
 **/
public class SocketMessageHandler implements MessageHandler {

    public void handle(WebSocketSession session, WebSocketMessage<?> message){
        if (message instanceof TextMessage){
            handleTextMessage(session,(TextMessage)message);
        }else if(message instanceof PingMessage){
            handlePingMessage(session,(PingMessage)message);
        }else if(message instanceof PongMessage){
            handlePongMessage(session,(PongMessage)message);
        }else if (message instanceof BinaryMessage){
            handleBinaryMessage(session,(BinaryMessage)message);
        }
    }

    static class MessageHandler{
        public static void handleTextMessage(WebSocketSession session, TextMessage message){
            try {
                session.close(CloseStatus.NOT_ACCEPTABLE.withReason("Text Message not support !"));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        public static void handlePingMessage(WebSocketSession session, PingMessage message){
            try {
                session.close(CloseStatus.NOT_ACCEPTABLE.withReason("Text Message not support !"));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        public static void handlePongMessage(WebSocketSession session, PongMessage message){
            try {
                session.close(CloseStatus.NOT_ACCEPTABLE.withReason("Text Message not support !"));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        public static void handleBinaryMessage(WebSocketSession session, BinaryMessage message){
            try {
                session.close(CloseStatus.NOT_ACCEPTABLE.withReason("Text Message not support !"));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
