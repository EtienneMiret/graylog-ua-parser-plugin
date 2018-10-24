package io.miret.etienne.graylog.ua_parser;

import org.graylog2.plugin.PluginModule;

public class UaParserModule extends PluginModule {
  @Override
  protected void configure () {
    addMessageProcessor (UaParser.class, UaParserDescriptor.class);
  }
}
