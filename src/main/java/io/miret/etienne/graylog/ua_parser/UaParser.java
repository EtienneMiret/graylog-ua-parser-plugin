package io.miret.etienne.graylog.ua_parser;

import org.graylog2.plugin.Message;
import org.graylog2.plugin.Messages;
import org.graylog2.plugin.messageprocessors.MessageProcessor;
import ua_parser.Client;
import ua_parser.Parser;

import javax.inject.Inject;

public class UaParser implements MessageProcessor {

  @Inject
  private Parser parser;

  @Override
  public Messages process (Messages messages) {
    for (Message message : messages) {
      String agent = message.getFieldAs (String.class, "agent");
      Client client = parser.parse (agent);
      message.addField ("client", client);
    }
    return messages;
  }

}
