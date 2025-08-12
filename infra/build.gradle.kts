/*plugins { base }

tasks.register("validateInfra") {
    group = "infrastructure"
    description = "Validate presence of infra files (placeholder)"

    // ⚠️ считаем путь на стадии конфигурации, а не внутри doLast
    val compose = layout.projectDirectory.file("docker-compose.yml")

    // объявим вход (опциональный) — дружелюбно к конфиг-кэшу
    inputs.files(compose).optional()

    doLast {
        val f = compose.get().asFile
        if (!f.exists()) {
            logger.lifecycle("No docker-compose.yml yet — OK for Sprint 0.")
        } else {
            logger.lifecycle("docker-compose.yml: ${f.absolutePath}")
        }
    }
}

tasks.named("assemble") { dependsOn("validateInfra") }*/
plugins { base }

// Плейсхолдер до появления docker-compose.yml
tasks.register("validateInfra") {
    group = "infrastructure"
    description = "Placeholder until docker-compose.yml appears"
    doLast {
        println("No docker-compose.yml yet — OK for Sprint 0.")
    }
}

// Включаем проверку в общий lifecycle
tasks.named("assemble") { dependsOn("validateInfra") }

