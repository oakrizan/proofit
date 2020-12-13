import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    java
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("org.springframework.boot") version "2.3.3.RELEASE"
    id("com.adarshr.test-logger") version "2.1.1"
}

apply(plugin = "java")
apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")
apply(plugin = "com.adarshr.test-logger")

java {
    sourceCompatibility = JavaVersion.VERSION_1_9
    targetCompatibility = JavaVersion.VERSION_1_9
}

ext {
    set("junit-jupiter.version", "5.7.0")
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("com.google.guava:guava:29.0-jre")
    implementation("org.springframework.boot:spring-boot-starter:2.4.0")
    implementation("com.codeborne:selenide:5.16.2")

    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.vintage:junit-vintage-engine")
    testImplementation("org.junit.jupiter:junit-jupiter-api:latest.release")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "junit")
        exclude(group = "org.hamcrest")
        exclude(group = "org.mockito")
    }
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:latest.release")
}

tasks {
    test {
        useJUnitPlatform()
        systemProperty("wdm.chromeDriverVersion", "86")
        if (project.hasProperty("browser")) {
            systemProperty("browser", project.property("browser") ?: "chrome")
        }
        systemProperty("selenide.headless", "false")
        if (project.hasProperty("grid")) {
            systemProperty("browser.remote", "true")
            systemProperty("selenide.remote", "http://${project.property("grid")}:4444/wd/hub")
        }
    }
}

tasks.withType<BootJar> {
    enabled = false
}
tasks.withType<Jar> {
    enabled = false
}
