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
    implementation(kotlin("stdlib"))

    api("org.myddd.vertx:myddd-vertx-domain:${rootProject.extra["myddd_vertx_version"]}")
    api("org.myddd.vertx:myddd-vertx-ioc-api:${rootProject.extra["myddd_vertx_version"]}")
    api("org.myddd.vertx:myddd-vertx-base-api:${rootProject.extra["myddd_vertx_version"]}")

    implementation("org.myddd.vertx:myddd-vertx-repository-api:${rootProject.extra["myddd_vertx_version"]}")
    testImplementation("io.vertx:vertx-junit5:${rootProject.extra["vertx_version"]}")
    testImplementation(project(":myddd-gs-infra"))
}
