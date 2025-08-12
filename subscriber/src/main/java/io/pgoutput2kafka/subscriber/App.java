package io.pgoutput2kafka.subscriber;

import io.pgoutput2kafka.common.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        Config cfg = Config.load();
        log.info("Subscriber starting... version={}", Version.get());

        // Не логируем пароль!
        log.info("PG {}@{}:{}/{} publication={} slot={} startLsn={}",
                mask(cfg.pgUser()), cfg.pgHost(), cfg.pgPort(), cfg.pgDb(),
                cfg.pgPublication(), cfg.pgSlot(), emptyToDash(cfg.pgStartLsn()));
        log.info("Kafka bootstrap={} topic={} clientId={} acks={} compression={}",
                cfg.kafkaBootstrap(), cfg.topicRaw(), cfg.kafkaClientId(),
                cfg.kafkaAcks(), cfg.kafkaCompression());

        // TODO: здесь подключение к PG logical replication и продюсер Kafka
        log.info("OK. (config loaded)");
    }

    private static String mask(String s) {
        if (s == null || s.length() <= 2) return "***";
        return s.charAt(0) + "***" + s.charAt(s.length()-1);
    }
    private static String emptyToDash(String s) { return (s == null || s.isBlank()) ? "-" : s; }
}
