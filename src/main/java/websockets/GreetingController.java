package websockets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;
import websockets.service.Eliza;


import java.util.Scanner;

@Controller
public class GreetingController {
    private static final Logger LOG = LoggerFactory.getLogger(GreetingController.class);
    private Eliza eliza = new Eliza();

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/hello")
    public void greeting(String message) throws Exception {
        LOG.error("Server Message ... ");
        Scanner currentLine = new Scanner(message.toLowerCase());
        if (currentLine.findInLine("bye") == null) {
            LOG.info("Server recieved \"" + message + "\"");
            //session.getAsyncRemote().sendText(eliza.respond(currentLine));
            //session.getAsyncRemote().sendText("---");
            template.convertAndSend("/topic/greetings", eliza.respond(currentLine));
        } else {
            //session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE, "Alright then, goodbye!"));
            template.convertAndSend("/topic/greetings", "Alright then, goodbye!");
        }
    }



}
