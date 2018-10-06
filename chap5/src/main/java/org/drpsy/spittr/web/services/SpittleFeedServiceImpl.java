package org.drpsy.spittr.web.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.drpsy.spittr.data.neo4j.documents.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

/**
 * Created by drpsy on 07-Oct-18 (11:49).
 */
@Service
public class SpittleFeedServiceImpl implements SpittleFeedService {

  @Autowired
  private SimpMessageSendingOperations messaging;

  private Pattern pattern = Pattern.compile("@(\\S+)");

  /**
   * SimpMessagingTemplate makes it possible to send messages from anywhere in an application,
   * even without having received a message first.
   * @param spittle Spittle to send.
   */
  @Override
  public void broadcastSpittle(Spittle spittle) {
    messaging.convertAndSend("/topic/spittle_feed", spittle);
    this.broadcastPersSpittle(spittle);
  }

  /**
   * A variant that allows to send messages that target a specific user.
   * @param spittle Spittle to send.
   */
  @Override
  public void broadcastPersSpittle(Spittle spittle) {
    Matcher matcher = pattern.matcher(spittle.getMessage());
    if (matcher.find()) {
      String username = matcher.group(1);
      messaging.convertAndSendToUser(username, "/topic/spittle_person_feed", spittle);
    }
  }

}
