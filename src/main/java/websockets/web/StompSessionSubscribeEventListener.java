package websockets.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
public class StompSessionSubscribeEventListener implements
        ApplicationListener<SessionSubscribeEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(StompSessionSubscribeEventListener.class);

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void onApplicationEvent(SessionSubscribeEvent sessionSubscribeEvent) {
        try {
            Thread.sleep(1000);  //
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        template.convertAndSend("/topic/greetings", "The doctor is in.");
        template.convertAndSend("/topic/greetings", "What's on your mind?");
        LOG.info("SUBSCRIBED LISTENER ");
    }
}