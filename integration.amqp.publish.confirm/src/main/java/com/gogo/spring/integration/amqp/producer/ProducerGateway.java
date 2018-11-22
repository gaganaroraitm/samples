package com.gogo.spring.integration.amqp.producer;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Payload;

@MessagingGateway
public interface ProducerGateway {

	@Gateway(requestChannel = "message-write-si-channel.caller")
	void publishMessageCallerPolicy(@Payload String message);
	
}
