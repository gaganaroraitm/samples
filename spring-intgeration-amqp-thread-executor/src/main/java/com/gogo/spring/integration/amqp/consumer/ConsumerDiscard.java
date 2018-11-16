package com.gogo.spring.integration.amqp.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ConsumerDiscard {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerDiscard.class);

	@ServiceActivator(inputChannel = "message-write-sa-channel-discard")
	public void consume(@Payload String message) throws InterruptedException {
		Thread.sleep(1000);
		LOGGER.info("Message -- " + message);
	}
}
