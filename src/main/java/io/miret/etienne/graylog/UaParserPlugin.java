package io.miret.etienne.graylog;

import com.google.common.collect.Lists;
import org.graylog2.plugin.Plugin;
import org.graylog2.plugin.PluginModule;

import java.util.Collection;

/**
 * Implement the Plugin interface here.
 */
public class UaParserPlugin implements Plugin {
  @Override
  public Collection<PluginModule> modules () {
    return Lists.newArrayList ((PluginModule) new UaParserModule ());
  }
}
