plugins {
    application
    jacoco
    id("com.github.ben-manes.versions") version "0.52.0"
    checkstyle
    id("org.sonarqube") version "6.2.0.5505"
}

sonar {
    properties {
        property("sonar.projectKey", "KulikovGM_java-project-71")
        property("sonar.organization", "kulikovgm")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

application {
    mainClass = "hexlet.code.App"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

@Suppress("kotlin:S6624")
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("info.picocli:picocli:4.7.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.19.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.14.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    reports { xml.required.set(true) }
}

tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}

