plugins { }

allprojects {
    group = providers.gradleProperty("group").get()
    version = providers.gradleProperty("version").get()
}

subprojects {
    plugins.withId("java") {
        the<JavaPluginExtension>().toolchain {
            languageVersion.set(JavaLanguageVersion.of(
                providers.gradleProperty("javaVersion").get().toInt()
            ))
        }
        tasks.withType<JavaCompile>().configureEach {
            options.encoding = "UTF-8"
            options.release.set(providers.gradleProperty("javaVersion").get().toInt())
            options.compilerArgs.addAll(listOf("-Xlint:deprecation","-Xlint:unchecked"))
        }
        tasks.withType<Test>().configureEach { useJUnitPlatform() }
    }
}
