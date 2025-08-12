rootProject.name = "pgoutput2kafka"

pluginManagement {
    repositories { gradlePluginPortal(); mavenCentral() }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories { mavenCentral() }
    versionCatalogs {
        create("libs") {
            version("junit", "5.10.2")
            version("slf4j", "2.0.13")
            version("logback", "1.5.6")
            version("pgjdbc", "42.7.3")
            version("kafka", "3.7.1")

            library("junit-bom", "org.junit", "junit-bom").versionRef("junit")
            library("junit-api", "org.junit.jupiter", "junit-jupiter-api").withoutVersion()
            library("junit-engine", "org.junit.jupiter", "junit-jupiter-engine").withoutVersion()

            library("slf4j-api", "org.slf4j", "slf4j-api").versionRef("slf4j")
            library("logback-classic", "ch.qos.logback", "logback-classic").versionRef("logback")

            library("postgresql", "org.postgresql", "postgresql").versionRef("pgjdbc")
            library("kafka-clients", "org.apache.kafka", "kafka-clients").versionRef("kafka")
        }
    }
}
include(":common", ":subscriber", ":infra")
