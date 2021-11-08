package giasuomt.demo.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import giasuomt.demo.websocket.dto.Message;
import giasuomt.demo.websocket.dto.ResponseMessage;

@Controller
public class MessageController {
	
	@MessageMapping("/message") //Nhận về app 
	@SendTo("/topic/messages") //Gửi đến Broker (đã đc subcribe ở fe)
	public ResponseMessage getMessage(Message message) throws Exception {
		Thread.sleep(1000);
		return new ResponseMessage("Hello, " + HtmlUtils.htmlEscape(message.getMessageContent()) + "!");
	}
}
