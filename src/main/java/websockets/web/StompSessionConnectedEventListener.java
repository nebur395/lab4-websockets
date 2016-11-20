package websockets.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import websockets.GreetingController;

@Component
public class StompSessionConnectedEventListener implements
        ApplicationListener<SessionConnectedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingController.class);

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void onApplicationEvent(SessionConnectedEvent sessionConnectedEvent) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionConnectedEvent
                .getMessage());
        template.convertAndSend("/topic/greetings", "Hola123");
        template.convertAndSend("/topic/greetings", "Hola21312312");

        LOG.error("CONNECTED LISTENER " + headerAccessor.toString());
    }
}
