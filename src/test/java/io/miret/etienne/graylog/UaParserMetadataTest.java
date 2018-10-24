package io.miret.etienne.graylog;

import org.graylog2.plugin.Version;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UaParserMetadataTest {

  @Test
  public void should_generate_version () {
    Version actual = new UaParserMetadata ().getVersion ();

    assertThat (actual).isNotNull ();
  }

}
