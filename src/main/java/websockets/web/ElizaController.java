package websockets.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import websockets.service.Eliza;


import java.util.Scanner;

@Controller
public class ElizaController {
    private static final Logger LOG = LoggerFactory.getLogger(ElizaController.class);
    private Eliza eliza = new Eliza();

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/doctor")
    public void greeting(String message) throws Exception {
        LOG.error("Server Message ... ");
        Scanner currentLine = new Scanner(message.toLowerCase());
        if (currentLine.findInLine("bye") == null) {
            LOG.info("Server recieved \"" + message + "\"");
            template.convertAndSend("/topic/eliza", "\"" + eliza.respond(currentLine) + "\"");
        } else {
            template.convertAndSend("/topic/eliza", "\"Alright then, goodbye!\"");
        }
    }
}
