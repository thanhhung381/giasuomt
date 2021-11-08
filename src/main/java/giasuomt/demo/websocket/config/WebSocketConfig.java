package giasuomt.demo.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	@Override
	public void configureMessageBroker(final MessageBrokerRegistry registry) { //implements the default method in WebSocketMessageBrokerConfigurer to configure the message broker
		registry.enableSimpleBroker("/topic"); //desinationPrefixes - có thể đặt tên gì tuỳ ý, để fe subcribe một path để backend gửi trả lại.
		registry.setApplicationDestinationPrefixes("/ws"); //để khi fe gửi data lên API thì gửi vào /ws/[tên API]
	}
	
	@Override
	public void registerStompEndpoints(final StompEndpointRegistry registry) {
		registry.addEndpoint("/our-websocket").withSockJS(); //path - có thể đặt tên tuỳ ý, để frontend gọi tới để mở cổng kết nối Websocket
	}
}
