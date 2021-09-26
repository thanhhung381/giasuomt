package giasuomt.demo.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import giasuomt.demo.websocket.dto.Message;
import giasuomt.demo.websocket.service.WSService;

@Controller
public class WSController {
	@Autowired
	private WSService service;
	
	@PostMapping("/send-message")
	public void sendMessage(@RequestBody Message message) {
		service.notifyFrontend(message.getMessageContent());
	}
}
