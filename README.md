# Graylog2 Plugin - UaParser

This plugin for [Graylog 2.4][1] provides a message processor that will parse
User-Agent strings from the "agent" field of messages.

## Build

* Run `mvn package` to build a JAR file.
* Run `mvn jdeb:jdeb` and `mvn rpm:rpm` to create a DEB and RPM package
  respectively.

[1]: https://www.graylog.org/
