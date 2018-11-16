package com.gogo.spring.integration.amqp.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.integration.amqp.support.AmqpHeaderMapper;

@Configuration
@ConditionalOnClass(AmqpHeaderMapper.class)
@ImportResource("rabbit-integration.xml")
public class RabbitConfig {

	@Value("${spring.rabbitmq.host:localhost}")
	private String rabbitHost;

	@Value("${spring.rabbitmq.port:5672}")
	private int rabbitPort;

	@Value("${spring.rabbitmq.username:guest}")
	private String rabbitUser;

	@Value("${spring.rabbitmq.password:guest}")
	private String rabbitPassword;

	@Value("${spring.rabbitmq.vhost:/}")
	private String rabbitVHost;

	@Value("${spring.rabbitmq.requested.heartbeat:10}")
	private int rabbitRequestedHeartBeat;

	@Bean
	public SimpleMessageConverter defaultMessageConverter() {
		final SimpleMessageConverter converter = new SimpleMessageConverter();
		return converter;
	}

	@Bean
	public MessageConverter messageConverter() {
		return defaultMessageConverter();
	}

	@Bean
	@Primary
	public ConnectionFactory rabbitConnectionFactory() {
		final CachingConnectionFactory rabbitConnectionFactory = new CachingConnectionFactory();

		rabbitConnectionFactory.setHost(rabbitHost);
		rabbitConnectionFactory.setPort(rabbitPort);
		rabbitConnectionFactory.setUsername(rabbitUser);
		rabbitConnectionFactory.setPassword(rabbitPassword);
		rabbitConnectionFactory.setVirtualHost(rabbitVHost);
		rabbitConnectionFactory.setRequestedHeartBeat(rabbitRequestedHeartBeat);

		return rabbitConnectionFactory;
	}

	@Bean
	@Primary
	public RabbitAdmin amqpAdmin() {
		return new RabbitAdmin(rabbitConnectionFactory());
	}

	@Bean
	@Primary
	public RabbitTemplate amqpTemplate(final ConnectionFactory rabbitConnectionFactory) {
		final RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
		template.setMessageConverter(messageConverter());
		return template;
	}

}
