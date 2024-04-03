import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.9.23"
    val springBootVersion = "3.2.4"
    val springDependencyManagerVersion = "1.1.4"

    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version springDependencyManagerVersion

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
}

repositories {
    mavenCentral()
}

val testContainersVersion = "1.19.7"
val kotlinCoroutinesVersion = "1.8.0"
val jacksonVersion = "2.17.0"

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:$testContainersVersion")
        mavenBom("org.jetbrains.kotlinx:kotlinx-coroutines-bom:$kotlinCoroutinesVersion")
        mavenBom("com.fasterxml.jackson:jackson-bom:$jacksonVersion")
    }
}

val postgresR2dbcVersion = "1.0.4.RELEASE"
val kotlinLoggingJvmVersion = "3.0.5"
val mockkVersion = "1.13.10"

dependencies {
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")

    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("io.github.microutils:kotlin-logging-jvm:$kotlinLoggingJvmVersion")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    runtimeOnly("org.postgresql:r2dbc-postgresql:$postgresR2dbcVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:testcontainers")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
    testImplementation("io.mockk:mockk:$mockkVersion")
}

configurations {
    testImplementation.configure {
        exclude(group = "org.mockito")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register("integrationTest", Test::class) {
    description = "Runs all integration tests"
    group = "verification"

    useJUnitPlatform {
        includeTags("integrationTest")
        excludeTags("unitTest")
    }
}

tasks.register("unitTest", Test::class) {
    description = "Runs all unit tests"
    group = "verification"

    useJUnitPlatform {
        includeTags("unitTest")
        excludeTags("integrationTest")
    }
}
