package giasuomt.demo.websocket.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.task.controller.TaskController;
import giasuomt.demo.task.model.Task;
import giasuomt.demo.task.service.ITaskService;
import giasuomt.demo.websocket.dto.Message;
import giasuomt.demo.websocket.dto.ResponseMessage;
import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
public class MessageController {
	private TaskController taskController;
	
	@MessageMapping("/message") //Nhận về app 
	@SendTo("/topic/messages") //Gửi đến Broker (đã đc subcribe ở fe)
	public ResponseMessage getMessage(Message message) throws Exception {
		Thread.sleep(1000);
		return new ResponseMessage("Hello, " + HtmlUtils.htmlEscape(message.getMessageContent()) + "!");
	}
	
	@MessageMapping("/taskCreated") //Nhận về app 
	@SendTo("/topic/tasks") //Gửi đến Broker (đã đc subcribe ở fe)
	public ResponseMessage getMessageTaskCreated(Message message) throws Exception {
		Thread.sleep(1000);
		return new ResponseMessage("New Task Created!");
	}
}
