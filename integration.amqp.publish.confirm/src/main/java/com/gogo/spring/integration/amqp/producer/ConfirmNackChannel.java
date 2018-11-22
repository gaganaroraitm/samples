package com.gogo.spring.integration.amqp.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ConfirmNackChannel {
	
	private static int messages = 0;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfirmNackChannel.class);

	@ServiceActivator(inputChannel = "confirm-nack-channel")
	public void consume(@Payload String message) throws InterruptedException {
		messages++;
		LOGGER.info("Total nack confirmations -- {}", messages);
	}
}
