package io.miret.etienne.graylog.ua_parser;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import com.google.inject.Guice;
import org.assertj.core.api.SoftAssertions;
import org.graylog2.plugin.Message;
import org.graylog2.plugin.Messages;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.graylog2.plugin.Message.FIELD_ID;

public class UaParserTest {

  @Inject
  private UaParser parser;

  @Before
  public void setUp () {
    Guice.createInjector ().injectMembers (this);
  }

  @Test
  public void should_noop_if_no_messages () {
    Messages actual = parser.process (Collections::emptyIterator);

    assertThat (actual).isEmpty ();
  }

  @Test
  public void should_parse_all_messages () {
    Message m0 = new Message (ImmutableMap.of ("agent", "foo", FIELD_ID, "0"));
    Message m1 = new Message (ImmutableMap.of ("agent", "bar", FIELD_ID, "1"));
    Message m2 = new Message (ImmutableMap.of ("agent", "baz", FIELD_ID, "2"));
    Messages input = () -> Iterators.forArray (m0, m1, m2);

    Messages actual = parser.process (input);

    assertThat (actual).isSameAs (input);
    assertThat (actual).extracting (m -> m.getField ("client.os.family"))
        .containsExactly ("Other", "Other", "Other");
  }

  @Test
  public void should_ignore_null_agent () {
    Message message = new Message (null, null, null);
    Messages input = () -> Iterators.forArray (message);

    Messages actual = parser.process (input);

    assertThat (actual).isSameAs (input);
    SoftAssertions softly = new SoftAssertions ();
    softly.assertThat (message.hasField ("client.userAgent.family")).isFalse ();
    softly.assertThat (message.hasField ("client.userAgent.version")).isFalse ();
    softly.assertThat (message.hasField ("client.userAgent.major")).isFalse ();
    softly.assertThat (message.hasField ("client.userAgent.minor")).isFalse ();
    softly.assertThat (message.hasField ("client.userAgent.patch")).isFalse ();
    softly.assertThat (message.hasField ("client.os.family")).isFalse ();
    softly.assertThat (message.hasField ("client.os.version")).isFalse ();
    softly.assertThat (message.hasField ("client.os.major")).isFalse ();
    softly.assertThat (message.hasField ("client.os.minor")).isFalse ();
    softly.assertThat (message.hasField ("client.os.patch")).isFalse ();
    softly.assertThat (message.hasField ("client.os.patchMinor")).isFalse ();
    softly.assertThat (message.hasField ("client.device.family")).isFalse ();
    softly.assertAll ();
  }

  @Test
  public void should_support_empty_agent () {
    Message message = new Message (ImmutableMap.of ("agent", "", FIELD_ID, "0"));
    Messages input = () -> Iterators.forArray (message);

    Messages actual = parser.process (input);

    assertThat (actual).isSameAs (input);
    SoftAssertions softly = new SoftAssertions ();
    softly.assertThat (message.hasField ("client.userAgent.family")).isFalse ();
    softly.assertThat (message.hasField ("client.userAgent.version")).isFalse ();
    softly.assertThat (message.hasField ("client.userAgent.major")).isFalse ();
    softly.assertThat (message.hasField ("client.userAgent.minor")).isFalse ();
    softly.assertThat (message.hasField ("client.userAgent.patch")).isFalse ();
    softly.assertThat (message.hasField ("client.os.family")).isFalse ();
    softly.assertThat (message.hasField ("client.os.version")).isFalse ();
    softly.assertThat (message.hasField ("client.os.major")).isFalse ();
    softly.assertThat (message.hasField ("client.os.minor")).isFalse ();
    softly.assertThat (message.hasField ("client.os.patch")).isFalse ();
    softly.assertThat (message.hasField ("client.os.patchMinor")).isFalse ();
    softly.assertThat (message.hasField ("client.device.family")).isFalse ();
    softly.assertAll ();
  }

  @Test
  public void should_support_firefox_62 () {
    Message message = new Message (ImmutableMap.of (
        "agent", "Firefox/62.0",
        FIELD_ID, "0"
    ));
    Messages input = () -> Iterators.forArray (message);

    Messages actual = parser.process (input);

    assertThat (actual).isSameAs (input);
    SoftAssertions softly = new SoftAssertions ();
    softly.assertThat (message.getField ("client.userAgent.family")).isEqualTo ("Firefox");
    softly.assertThat (message.getField ("client.userAgent.version")).isEqualTo ("62.0");
    softly.assertThat (message.getField ("client.userAgent.major")).isEqualTo ("62");
    softly.assertThat (message.getField ("client.userAgent.minor")).isEqualTo ("0");
    softly.assertThat (message.hasField ("client.userAgent.patch")).isFalse ();
    softly.assertThat (message.getField ("client.os.family")).isEqualTo ("Other");
    softly.assertThat (message.hasField ("client.os.version")).isFalse ();
    softly.assertThat (message.hasField ("client.os.major")).isFalse ();
    softly.assertThat (message.hasField ("client.os.minor")).isFalse ();
    softly.assertThat (message.hasField ("client.os.patch")).isFalse ();
    softly.assertThat (message.hasField ("client.os.patchMinor")).isFalse ();
    softly.assertThat (message.getField ("client.device.family")).isEqualTo ("Other");
    softly.assertAll ();
  }

  @Test
  public void should_support_MacOS_10_14 () {
    Message message = new Message (ImmutableMap.of (
        "agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:62.0)",
        FIELD_ID, "0"
    ));
    Messages input = () -> Iterators.forArray (message);

    Messages actual = parser.process (input);

    assertThat (actual).isSameAs (input);
    SoftAssertions softly = new SoftAssertions ();
    softly.assertThat (message.getField ("client.userAgent.family")).isEqualTo ("Other");
    softly.assertThat (message.hasField ("client.userAgent.version")).isFalse ();
    softly.assertThat (message.hasField ("client.userAgent.major")).isFalse ();
    softly.assertThat (message.hasField ("client.userAgent.minor")).isFalse ();
    softly.assertThat (message.hasField ("client.userAgent.patch")).isFalse ();
    softly.assertThat (message.getField ("client.os.family")).isEqualTo ("Mac OS X");
    softly.assertThat (message.getField ("client.os.version")).isEqualTo ("10.14");
    softly.assertThat (message.getField ("client.os.major")).isEqualTo ("10");
    softly.assertThat (message.getField ("client.os.minor")).isEqualTo ("14");
    softly.assertThat (message.hasField ("client.os.patch")).isFalse ();
    softly.assertThat (message.hasField ("client.os.patchMinor")).isFalse ();
    softly.assertThat (message.getField ("client.device.family")).isEqualTo ("Other");
    softly.assertAll ();
  }

}
