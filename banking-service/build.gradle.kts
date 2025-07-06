import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("com.palantir.docker") version "0.36.0"
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

tasks.named<BootJar>("bootJar") {
    mainClass.set("org.roky.pay.banking.BankingApplicationKt")
}

dependencies {
    implementation(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("jakarta.persistence:jakarta.persistence-api")
    implementation("org.springframework:spring-tx")
    implementation("org.flywaydb:flyway-mysql")
    implementation("org.flywaydb:flyway-core")

    testImplementation("io.mockk:mockk")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    implementation("jakarta.validation:jakarta.validation-api")
    implementation("org.hibernate.validator:hibernate-validator")

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
