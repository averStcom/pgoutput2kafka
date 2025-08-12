plugins { application }

dependencies {
    implementation(project(":common"))
    implementation(libs.slf4j.api)
    runtimeOnly(libs.logback.classic)
    implementation("io.github.cdimascio:dotenv-java:3.0.0")

    implementation(libs.postgresql)     // логическая репликация (pgjdbc)
    implementation(libs.kafka.clients)  // продюсер Kafka

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.api)
    testRuntimeOnly(libs.junit.engine)
}

application {
    mainClass.set("io.pgoutput2kafka.subscriber.App")
}
