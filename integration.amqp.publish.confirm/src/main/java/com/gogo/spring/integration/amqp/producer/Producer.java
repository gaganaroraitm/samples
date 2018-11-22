package com.gogo.spring.integration.amqp.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {

	private static int messages = 0;

	private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

	@Autowired
	private ProducerGateway producerGateway;

	@GetMapping(value = "/generate/{no_of_messages}")
	public ResponseEntity<Void> publishMessage(@PathVariable("no_of_messages") Integer noOfMessages) {
		try {
			for (int i = 0; i < noOfMessages; i++) {
				producerGateway.publishMessageCallerPolicy("Message" + i);
				Thread.sleep(10);
				messages++;
			}
		} catch (Exception e) {
			LOGGER.error("Error -> {}", e.getMessage());
		} finally {
			LOGGER.info("No of messages published -> {}", messages);
		}
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

}
