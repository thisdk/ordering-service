import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "io.thisdk.github"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_11

plugins {
    war
    id("org.springframework.boot") version "2.4.1"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    implementation("cn.hutool:hutool-all:4.5.1")
    implementation("io.swagger:swagger-models:1.5.22")
    implementation("io.jsonwebtoken:jjwt:0.6.0")
    implementation("com.alibaba:fastjson:1.2.70")
    implementation("org.apache.commons:commons-io:3.7")
    implementation("eu.bitwalker:UserAgentUtils")

}

repositories {1
    mavenCentral()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
