import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    java
    kotlin("jvm") version "1.8.20"
}

group = "dev.donam.exr"
version = "1.0.0-SNAPSHOT"
extra["myddd_vertx_version"] = "0.3.3-SNAPSHOT"

extra["vertx_version"] = "4.2.2"
extra["version"] = version

extra["log4j_version"] = "2.14.1"
extra["jackson_version"] = "2.12.1"
extra["javax_persistence_version"] = "2.2.1"
extra["mockito_version"] = "4.6.1"
extra["junit5_version"] = "5.8.2"

extra["hibernate_reactive_version"] = "1.1.1.Final"


allprojects {

    // don't cache changing modules at all
    configurations.all {
        resolutionStrategy.cacheChangingModulesFor(0, "seconds")
    }

    repositories {
        maven {
            setUrl("https://maven.myddd.org/releases/")
        }
        maven {
            setUrl("https://maven.myddd.org/snapshots/")
        }

        maven {
            setUrl("https://maven.aliyun.com/repository/public/")
        }
        maven {
            setUrl("https://maven.aliyun.com/repository/spring/")
        }

        mavenCentral()

        maven {
            setUrl("https://jitpack.io")
        }

    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

}

subprojects {

    apply(plugin = "java")
    dependencies{
        implementation(kotlin("stdlib"))

        implementation("io.vertx:vertx-core:${rootProject.extra["vertx_version"]}")
        implementation("io.vertx:vertx-lang-kotlin:${rootProject.extra["vertx_version"]}")
        implementation("io.vertx:vertx-lang-kotlin-coroutines:${rootProject.extra["vertx_version"]}")

        implementation("org.myddd.vertx:myddd-vertx-ioc-guice:${rootProject.extra["myddd_vertx_version"]}")

        testImplementation("io.vertx:vertx-junit5:${rootProject.extra["vertx_version"]}")
        testImplementation("org.junit.jupiter:junit-jupiter:${rootProject.extra["junit5_version"]}")
        testImplementation("org.mockito:mockito-core:${rootProject.extra["mockito_version"]}")
        testImplementation("io.vertx:vertx-pg-client:${rootProject.extra["vertx_version"]}")

        testImplementation("org.myddd.vertx:myddd-vertx-ioc-guice:${rootProject.extra["myddd_vertx_version"]}")

        testImplementation("org.apache.logging.log4j:log4j-core:${rootProject.extra["log4j_version"]}")
    }

}
dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}