package org.drpsy.spittr.config;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

/**
 * Created by drpsy on 23-Aug-18 (23:52).
 */
@Configuration
@EnableJms
public class MessagingConfig {

  private static final Logger LOG = LogManager.getLogger(MessagingConfig.class);

  @Autowired
  private Environment environment;

  @Bean
  public ActiveMQConnectionFactory connectionFactory() {
    ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
    factory.setTrustAllPackages(true);
    return factory;
  }

  @Bean
  public JmsTemplate jmsTemplate() {
    JmsTemplate jmsTemplate = new JmsTemplate();
    jmsTemplate.setConnectionFactory(connectionFactory());  // To know how to get connections to the message broker
    jmsTemplate.setDefaultDestinationName("spittle.alert.queue");
    jmsTemplate.setMessageConverter(new SimpleMessageConverter());
    return jmsTemplate;
  }

  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory());
    factory.setDestinationResolver(destinationResolver(environment));
    factory.setConcurrency("1");
    factory.setSessionTransacted(true);
    factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
    return factory;
  }

  /**
   * Uses properties to attempt to map a JMS destination name to an actual queue/topic name.
   *
   * @return
   */
  @Bean
  public DestinationResolver destinationResolver(Environment environment) {
    return new DestinationResolver() {
      private DestinationResolver dynamicDestinationResolver = new DynamicDestinationResolver();

      @Override
      public Destination resolveDestinationName(Session session, String destinationName, boolean pubSubDomain) throws JMSException {
        String dName = environment.getProperty("jms.destination." + destinationName, destinationName);
        LOG.info("Destination name \'" + destinationName + "\' resolved to: \'" + dName + "\'");
        return dynamicDestinationResolver.resolveDestinationName(session, dName, pubSubDomain);
      }
    };
  }

}
