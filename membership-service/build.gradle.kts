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

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
}

tasks.register("prepareKotlinBuildScriptModel"){}
