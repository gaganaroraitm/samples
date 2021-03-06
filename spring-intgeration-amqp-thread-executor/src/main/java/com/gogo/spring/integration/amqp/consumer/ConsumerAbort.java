package com.gogo.spring.integration.amqp.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ConsumerAbort {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerAbort.class);

	@ServiceActivator(inputChannel = "message-write-sa-channel-abort")
	public void consume(@Payload String message) throws InterruptedException{
		Thread.sleep(1000);
		LOGGER.info("Message -- " + message);
	}
}
