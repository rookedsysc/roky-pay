import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm")
    kotlin("plugin.spring") version "1.9.25"
    id("com.palantir.docker") version "0.36.0"
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
}

tasks.named<BootJar>("bootJar") {
    mainClass.set("org.roky.pay.money.MoneyApplicationKt")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.flywaydb:flyway-mysql")
    implementation("org.flywaydb:flyway-core")

    testImplementation("io.mockk:mockk")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    testImplementation("com.h2database:h2")
}

tasks.register("prepareKotlinBuildScriptModel") {}

docker {
    println(tasks.named<BootJar>("bootJar").get().outputs.files)
    name = "${rootProject.name}-${project.name}:${project.version}"
    setDockerfile(File("./Dockerfile"))
    files(tasks.named<BootJar>("bootJar").get().outputs.files)
    buildArgs(
        mapOf(
            "JAR_FILE" to tasks.named<BootJar>("bootJar").get().outputs.files.singleFile.name
        )
    )
}
