import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	id("com.diffplug.spotless") version "6.25.0"
	id("com.netflix.dgs.codegen") version "6.1.5"
	kotlin("jvm") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"
}

group = "com.autodata"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencyManagement {
	imports {
		mavenBom("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:latest.release")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.kotest:kotest-runner-junit5:latest.release")
	testImplementation("io.kotest:kotest-property:latest.release")
	testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
	kotlin {
		targetExclude("build/**/*")
		ktlint()
	}
}

tasks.withType<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask> {
	generateClient = true
	language = "kotlin"
	generateKotlinNullableClasses = true
	generateKotlinClosureProjections = true
}

afterEvaluate {
	tasks.findByName("spotlessApply")?.let { spotlessApply ->
		tasks.withType(KotlinCompile::class).configureEach {
			finalizedBy(spotlessApply)
		}
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