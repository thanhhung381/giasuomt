package giasuomt.demo.websocket.dto;

public class ResponseMessage {
	private String content;
	
	//constructor
	public ResponseMessage() {	
	}
	
	public ResponseMessage(String content) {
		this.content = content;
	}

	//getter,setter
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}	
}
