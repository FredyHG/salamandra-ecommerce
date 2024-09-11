plugins {
	java
	id("org.springframework.boot") version "3.3.2"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "dev.fredyhg"
version = "0.0.1-SNAPSHOT"
extra["springCloudVersion"] = "2023.0.3"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.instancio:instancio-junit:5.0.1")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.1.3")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
	testImplementation("com.h2database:h2:2.2.224")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("com.google.code.gson:gson:2.11.0")

}


dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
