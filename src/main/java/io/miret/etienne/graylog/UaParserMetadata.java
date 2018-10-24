package io.miret.etienne.graylog;

import org.graylog2.plugin.PluginMetaData;
import org.graylog2.plugin.ServerStatus;
import org.graylog2.plugin.Version;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;
import java.util.Set;

import static java.util.Collections.emptySet;

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
    Properties properties = new Properties ();
    try (InputStream metadata = UaParserMetadata.class.getResourceAsStream ("metadata.properties")) {
      properties.load (metadata);
    } catch (IOException e) {
      throw new RuntimeException ("Could not read metadata.properties.", e);
    }
    com.github.zafarkhaja.semver.Version semver =
        com.github.zafarkhaja.semver.Version.valueOf (properties.getProperty ("version"));
    return new Version (semver);
  }

  @Override
  public String getDescription () {
    return "This plugin uses the ua-parser library (https://github.com/ua-parser) to parse user-agent strings.";
  }

  @Override
  public Version getRequiredVersion () {
    com.github.zafarkhaja.semver.Version semver =
        com.github.zafarkhaja.semver.Version.valueOf ("2.4.0");
    return new Version (semver);
  }

  @Override
  public Set<ServerStatus.Capability> getRequiredCapabilities () {
    return emptySet ();
  }

}
