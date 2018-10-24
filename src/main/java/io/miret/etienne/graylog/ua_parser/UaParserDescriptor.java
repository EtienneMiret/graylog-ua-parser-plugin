package io.miret.etienne.graylog.ua_parser;

import org.graylog2.plugin.messageprocessors.MessageProcessor;

public class UaParserDescriptor implements MessageProcessor.Descriptor {

  @Override
  public String name () {
    return "UA parser";
  }

  @Override
  public String className () {
    return UaParser.class.getName ();
  }

}
