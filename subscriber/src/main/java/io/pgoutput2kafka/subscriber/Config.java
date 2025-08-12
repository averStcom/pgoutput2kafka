package io.pgoutput2kafka.subscriber;

import io.github.cdimascio.dotenv.Dotenv;
import java.util.Optional;
import java.time.Duration;

public record Config(
        // Kafka
        String kafkaBootstrap,
        String topicRaw,
        String kafkaClientId,
        String kafkaAcks,
        int kafkaLingerMs,
        int kafkaBatchSize,
        String kafkaCompression,
        // PG
        String pgHost,
        int pgPort,
        String pgDb,
        String pgUser,
        String pgPassword,
        String pgPublication,
        String pgSlot,
        String pgStartLsn,  // может быть пустым
        // Misc
        String logLevel,
        int metricsPort
) {
    public static Config load() {
        // .env в каталоге subscriber/ подхватится автоматически
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()   // чтобы не падать, если .env отсутствует
                .load();

        return new Config(
                req(dotenv, "KAFKA_BOOTSTRAP_SERVERS"),
                req(dotenv, "KAFKA_TOPIC_RAW"),
                opt(dotenv, "KAFKA_CLIENT_ID", "pgoutput2kafka-subscriber"),
                opt(dotenv, "KAFKA_ACKS", "all"),
                optInt(dotenv, "KAFKA_LINGER_MS", 5),
                optInt(dotenv, "KAFKA_BATCH_SIZE", 32 * 1024),
                opt(dotenv, "KAFKA_COMPRESSION", "zstd"),
                req(dotenv, "PG_HOST"),
                optInt(dotenv, "PG_PORT", 5432),
                req(dotenv, "PG_DB"),
                req(dotenv, "PG_USER"),
                req(dotenv, "PG_PASSWORD"),
                req(dotenv, "PG_PUBLICATION"),
                req(dotenv, "PG_SLOT"),
                opt(dotenv, "PG_START_LSN", ""),
                opt(dotenv, "LOG_LEVEL", "INFO"),
                optInt(dotenv, "METRICS_PORT", 8081)
        );
    }

    private static String req(Dotenv d, String k) {
        String v = Optional.ofNullable(System.getenv(k)).orElse(d.get(k));
        if (v == null || v.isBlank()) {
            throw new IllegalArgumentException("Required env var is missing: " + k);
        }
        return v;
    }

    private static String opt(Dotenv d, String k, String def) {
        String v = Optional.ofNullable(System.getenv(k)).orElse(d.get(k));
        return (v == null || v.isBlank()) ? def : v;
    }

    private static int optInt(Dotenv d, String k, int def) {
        String v = Optional.ofNullable(System.getenv(k)).orElse(d.get(k));
        if (v == null || v.isBlank()) return def;
        try { return Integer.parseInt(v.trim()); }
        catch (NumberFormatException e) { throw new IllegalArgumentException("Bad int for " + k + ": " + v); }
    }
}
