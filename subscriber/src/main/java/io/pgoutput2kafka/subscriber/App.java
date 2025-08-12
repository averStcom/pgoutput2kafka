package io.pgoutput2kafka.subscriber;

import io.pgoutput2kafka.common.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        log.info("Subscriber starting... project version={}", Version.get());
        // TODO: init PG logical replication stream + publish 1:1 to Kafka
        log.info("OK. (placeholder)");
    }
}
