plugins {
    application
    id("java")
    id("com.github.ben-manes.versions") version "0.52.0"
    checkstyle
    id("org.sonarqube") version "6.0.1.5171"
}

application {
    mainClass = "hexlet.code.App"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("info.picocli:picocli:4.7.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.19.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}

sonar {
    properties {
        property("sonar.projectKey", "KulikovGM_java-project-71")
        property("sonar.organization", "kulikovgm")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}