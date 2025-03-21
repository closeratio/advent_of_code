import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES
import org.springframework.boot.gradle.tasks.bundling.BootJar

val currentYear = 2024

plugins {
    id("org.springframework.boot") version "3.4.4" apply false
    kotlin("jvm") version "2.1.20" apply false
    kotlin("plugin.spring") version "2.1.20" apply false

    java
    `java-test-fixtures`
}

subprojects {

    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.gradle.java")
    apply(plugin = "org.gradle.java-test-fixtures")

    repositories {
        mavenCentral()
    }

    group = "com.closeratio.aoc"
    version = "0.0.1-SNAPSHOT"
    java.sourceCompatibility = JavaVersion.VERSION_17

    dependencies {
        implementation(platform(BOM_COORDINATES))

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testFixturesImplementation("org.springframework.boot:spring-boot-starter-test")

        // Each year needs its own group because of a limitation/bug when IntelliJ imports the resolved gradle modules.
        if (parent?.project != rootProject) {
            group = "com.closeratio.aoc" + parent?.name
        }

        // Everything depends on the common module, including the year-specific common modules
        if (project.name != "common" || parent?.project != rootProject) {
            implementation(project(":common"))
            testImplementation(testFixtures(project(":common")))
        }

        // Every day within a year depends on that year's common module
        if (project.name.startsWith("day-")) {
            implementation(project(":${parent!!.name}:common"))
            testImplementation(testFixtures(project(":${parent!!.name}:common")))
        }

        // The single "app" module depends on every day in every year.
        if (project.name == "app") {
            (2015..currentYear).forEach { year ->
                (1..25).forEach { day ->
                    implementation(project(":$year:day-$day"))
                }
            }
        }
    }

    tasks.withType<KotlinCompile> {
        compilerOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = JvmTarget.JVM_17
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()

        maxHeapSize = "4g"
    }

    if (project.name != "app") {
        tasks.withType<BootJar> {
            enabled = false
        }

        tasks.withType<Jar> {
            enabled = true
        }
    }

}
