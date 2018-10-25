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
      if (agent != null) {
        Client client = parser.parse (agent);
        if (client.userAgent != null) {
          String version = String.format ("%s.%s.%s",
              client.userAgent.major, client.userAgent.minor, client.userAgent.patch);
          message.addField ("client.userAgent.family", client.userAgent.family);
          message.addField ("client.userAgent.version", version);
          message.addField ("client.userAgent.major", client.userAgent.major);
          message.addField ("client.userAgent.minor", client.userAgent.minor);
          message.addField ("client.userAgent.patch", client.userAgent.patch);
        }
        if (client.os != null) {
          String version = String.format ("%s.%s.%s",
              client.os.major, client.os.minor, client.os.patch);
          message.addField ("client.os.family", client.os.family);
          message.addField ("client.os.version", version);
          message.addField ("client.os.major", client.os.major);
          message.addField ("client.os.minor", client.os.minor);
          message.addField ("client.os.patch", client.os.patch);
          message.addField ("client.os.patchMinor", client.os.patchMinor);
        }
        if (client.device != null) {
          message.addField ("client.device.family", client.device.family);
        }
      }
    }
    return messages;
  }

}
