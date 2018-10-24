package io.miret.etienne.graylog;

import org.graylog2.plugin.PluginMetaData;
import org.graylog2.plugin.Version;

import java.net.URI;

/**
 * Implement the PluginMetaData interface here.
 */
public class UaParserMetadata implements PluginMetaData {

  @Override
  public String getUniqueId () {
    return "io.miret.etienne.graylog:graylog-plugin-ua-parser";
  }

  @Override
  public String getName () {
    return "UA Parser";
  }

  @Override
  public String getAuthor () {
    return "Etienne Miret <etienne.miret@ens-lyon.org>";
  }

  @Override
  public URI getURL () {
    return URI.create ("https://github.com/EtienneMiret/graylog-plugin-ua-parser");
  }

  @Override
  public Version getVersion () {
    return new Version (1, 0, 0, "SNAPSHOT");
  }

  @Override
  public String getDescription () {
    return "This plugin uses the ua-parser library (https://github.com/ua-parser) to parse user-agent strings.";
  }

  @Override
  public Version getRequiredVersion () {
    return new Version (2, 4, 0);
  }

}
