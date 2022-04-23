package giasuomt.demo.websocket.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
	private String messageContent;

	//getter,setter
	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
}
