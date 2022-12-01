import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("org.springframework.boot") version "3.0.0" apply false
	id("io.spring.dependency-management") version "1.1.0" apply false
	kotlin("jvm") version "1.7.21" apply false
	kotlin("plugin.spring") version "1.7.21" apply false

	java
}

subprojects {

	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "org.gradle.java")

	repositories {
		mavenCentral()
	}

	group = "com.closeratio"
	version = "0.0.1-SNAPSHOT"
	java.sourceCompatibility = JavaVersion.VERSION_17

	dependencies {

		testImplementation("org.springframework.boot:spring-boot-starter-test")

		if (project.name != "common") {
			implementation(project(":common"))
		}

		if (project.name == "app") {
			(1..25).forEach {
				implementation(project(":day_$it"))
			}
		}
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "17"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	if (project.name != "app") {
		tasks.withType<BootJar>() {
			enabled = false
		}

		tasks.withType<Jar>() {
			enabled = true
		}
	}

}
