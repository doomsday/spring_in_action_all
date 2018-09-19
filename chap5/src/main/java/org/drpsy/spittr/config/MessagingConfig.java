package org.drpsy.spittr.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.drpsy.spittr.messaging.SpittleAlertHandler;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by drpsy on 23-Aug-18 (23:52).
 */
@Configuration
@EnableRabbit
public class MessagingConfig {

  private static final Logger LOGGER = LogManager.getLogger(MessagingConfig.class);

  private static final String topicExchangeName = "spring.spittle.exchange";
  private static final String queueName = "spittle.queue";
  private static final String routingKey = "spittle.alerts.#";

  @Bean
  public RabbitTemplate rabbitTemplate(){
    RabbitTemplate rabbitTemplate = new RabbitTemplate();
    rabbitTemplate.setConnectionFactory(connectionFactory());
    rabbitTemplate.setExchange(topicExchangeName);
    rabbitTemplate.setRoutingKey(routingKey);
    return rabbitTemplate;
  }

  /*
  This queue must be created in RabbitMQ Management Console!
   */
  @Bean
  Queue queue() {
    return new Queue(queueName, true);
  }

  /*
  Exchange must be must be created and bound to queue (with corresponding routing key) in RabbitMQ Management Console!
   */
  @Bean
  TopicExchange exchange() {
    return new TopicExchange(topicExchangeName);
  }

  @Bean
  Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(routingKey);
  }

  @Bean
  MessageListenerAdapter listenerAdapter(SpittleAlertHandler receiver) {
    return new MessageListenerAdapter(receiver, "handleSpittleAlert");
  }

  @Bean
  public ConnectionFactory connectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
    connectionFactory.setUsername("guest");
    connectionFactory.setPassword("guest");
    return connectionFactory;
  }

  @Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(queueName);
    container.setMessageListener(listenerAdapter);
    return container;
  }

}
