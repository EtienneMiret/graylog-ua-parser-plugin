package io.miret.etienne.graylog.ua_parser;

import org.graylog2.plugin.Messages;
import org.graylog2.plugin.messageprocessors.MessageProcessor;

public class UaParser implements MessageProcessor {

  @Override
  public Messages process (Messages messages) {
    return messages;
  }

}
