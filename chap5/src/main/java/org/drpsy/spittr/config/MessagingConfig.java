package org.drpsy.spittr.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by drpsy on 23-Aug-18 (23:52).
 */
@Configuration
@EnableRabbit
public class MessagingConfig {

  private static final Logger LOGGER = LogManager.getLogger(MessagingConfig.class);

  @Value("${exchange.name}")
  private String exchangeName;

  @Value("${spittle.queue.name}")
  private String spittleQueueName;

  @Value("${spittr.queue.name}")
  private String spittrQueueName;

  @Value("${spittle.routing.key}")
  private String spittleRoutingKey;

  @Value("${spittr.routing.key}")
  private String spittrRoutingKey;

  @Bean
  public RabbitTemplate rabbitTemplate() {
    RabbitTemplate rabbitTemplate = new RabbitTemplate();
    rabbitTemplate.setConnectionFactory(connectionFactory());
    rabbitTemplate.setExchange(exchangeName);
    return rabbitTemplate;
  }

  /*
  Exchanges must be must be created and bound to spittleQueue (with corresponding routing key)
  in RabbitMQ Management Console!
 */
  @Bean
  TopicExchange exchange() {
    return new TopicExchange(exchangeName);
  }

  /*
  This queues must be created in RabbitMQ Management Console!
   */
  @Bean
  Queue spittleQueue() {
    return new Queue(spittleQueueName, true);
  }

  @Bean
  Queue spittrQueue() {
    return new Queue(spittrQueueName, true);
  }

  @Bean
  Binding spittleBinding() {
    return BindingBuilder.bind(spittleQueue()).to(exchange()).with(spittleRoutingKey);
  }

  @Bean
  Binding spittrBinding() {
    return BindingBuilder.bind(spittrQueue()).to(exchange()).with(spittrRoutingKey);
  }

  @Bean
  public ConnectionFactory connectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
    connectionFactory.setUsername("guest");
    connectionFactory.setPassword("guest");
    return connectionFactory;
  }

  @Bean
  public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory());
    factory.setConcurrentConsumers(10);
    factory.setMaxConcurrentConsumers(20);
    return factory;
  }

}
