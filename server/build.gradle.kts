import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.3.2.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("jvm") version "1.3.71"
    kotlin("plugin.spring") version "1.3.71"
}

group = "dev.jonaz.backend"
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
    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("org.atmosphere:atmosphere-runtime:2.6.1")
    implementation("org.atmosphere:nettosphere:3.2.1")
    implementation("javax.servlet:javax.servlet-api:4.0.1")

    implementation("com.zaxxer:HikariCP:3.2.0")
    implementation("org.postgresql:postgresql:42.2.2")

    implementation("com.google.code.gson:gson:2.8.6")

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

tasks.register<Copy>("copyDockerfile") {
    from("$projectDir/Dockerfile")
    into("$buildDir/libs")
}

tasks.replace("build").dependsOn("copyDockerfile")
