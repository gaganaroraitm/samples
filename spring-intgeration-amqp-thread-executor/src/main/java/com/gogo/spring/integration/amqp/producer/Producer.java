package com.gogo.spring.integration.amqp.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {

	@Autowired
	private ProducerGateway producerGateway;

	@GetMapping(value = "/generate/{policy}/{no_of_messages}")
	public ResponseEntity<Void> publishMessage(@PathVariable("policy") String policy,
			@PathVariable("no_of_messages") Integer noOfMessages) {
		for (int i = 0; i < noOfMessages; i++) {
			switch (policy) {
			case "caller":
				producerGateway.publishMessageCallerPolicy("Message" + i);
				break;
			case "abort":
				producerGateway.publishMessageAbortPolicy("Message" + i);
				break;
			case "discard":
				producerGateway.publishMessageDiscardPolicy("Message" + i);
				break;
			default:
				break;
			}
		}
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

}
