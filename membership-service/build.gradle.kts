plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("plugin.jpa")
}

dependencies {
    implementation("org.flywaydb:flyway-mysql:10.21.0")
    implementation("org.flywaydb:flyway-core:10.21.0")

    testImplementation("io.mockk:mockk:1.13.11")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    testImplementation("com.h2database:h2:2.3.232")
}

tasks.register("prepareKotlinBuildScriptModel") {}
