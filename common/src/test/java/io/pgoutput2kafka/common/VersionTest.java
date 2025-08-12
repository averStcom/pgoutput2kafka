package io.pgoutput2kafka.common;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
class VersionTest {
    @Test void versionIsPresent() { assertEquals("0.0.0", Version.get()); }
}
