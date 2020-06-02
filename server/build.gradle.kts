import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("jvm") version "1.3.71"
    kotlin("plugin.spring") version "1.3.71"
}

group = "dev.jonaz.backend"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_11

setBuildDir("../build")

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.security:spring-security-core:5.3.2.RELEASE")

    implementation("com.zaxxer:HikariCP:3.2.0")
    implementation("org.postgresql:postgresql:42.2.2")
    implementation("org.reflections:reflections:0.9.9")
    implementation("com.corundumstudio.socketio:netty-socketio:1.7.12")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.exposed:exposed-core:0.22.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.22.1")
    implementation("org.jetbrains.exposed:exposed-java-time:0.22.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
