package giasuomt.demo.websocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import giasuomt.demo.websocket.dto.ResponseMessage;

@Service
public class WSService {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	public void notifyFrontend(String message) {
		ResponseMessage response = new ResponseMessage(message);
		messagingTemplate.convertAndSend("/topic/messages", response);
	}
}
