plugins {
    id("com.palantir.docker") version "0.36.0"
}

dependencies {
    implementation("org.flywaydb:flyway-mysql:10.21.0")
    implementation("org.flywaydb:flyway-core:10.21.0")

    testImplementation("io.mockk:mockk:1.13.11")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    testImplementation("com.h2database:h2:2.3.232")
}

tasks.register("prepareKotlinBuildScriptModel") {}

docker {
    println(tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar").get().outputs.files)
    name = "${rootProject.name}-${project.name}:${project.version}"
    setDockerfile(File("./Dockerfile"))
    files(tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar").get().outputs.files)
    buildArgs(
        mapOf(
            "JAR_FILE" to tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar").get().outputs.files.singleFile.name
        )
    )
}
