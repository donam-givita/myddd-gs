plugins {
    java
    kotlin("jvm")
}

group = "dev.donam.exr"
version = rootProject.extra["version"]!!

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

dependencies {
    //other layer
    implementation(project(":myddd-gs-domain"))

    //api
    api("org.myddd.vertx:myddd-vertx-ioc-api:${rootProject.extra["myddd_vertx_version"]}")
    api("org.myddd.vertx:myddd-vertx-repository-api:${rootProject.extra["myddd_vertx_version"]}")
    api("org.myddd.vertx:myddd-vertx-repository-hibernate:${rootProject.extra["myddd_vertx_version"]}")

    //api implementation
    api("org.hibernate.reactive:hibernate-reactive-core:${rootProject.extra["hibernate_reactive_version"]}")
    api("org.myddd.vertx:myddd-vertx-base-provider:${rootProject.extra["myddd_vertx_version"]}")
    implementation("com.fasterxml.jackson.core:jackson-databind:${rootProject.extra["jackson_version"]}")
}
