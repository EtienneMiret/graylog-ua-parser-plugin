package io.miret.etienne.graylog.ua_parser;

import org.graylog2.plugin.Plugin;
import org.graylog2.plugin.PluginMetaData;
import org.graylog2.plugin.PluginModule;

import java.util.Collection;
import java.util.Collections;

/**
 * Implement the Plugin interface here.
 */
public class UaParserPlugin implements Plugin {

  @Override
  public PluginMetaData metadata () {
    return new UaParserMetadata ();
  }

  @Override
  public Collection<PluginModule> modules () {
    return Collections.singletonList (new UaParserModule ());
  }

}
