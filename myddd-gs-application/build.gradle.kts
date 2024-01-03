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
    implementation("io.vertx:vertx-web-client:${rootProject.extra["vertx_version"]}")

    api(project(":myddd-gs-api"))
    implementation(project(":myddd-gs-domain"))
    api("org.myddd.vertx:myddd-vertx-querychannel-api:${rootProject.extra["myddd_vertx_version"]}")

    //依赖注入
    implementation("org.myddd.vertx:myddd-vertx-ioc-api:${rootProject.extra["myddd_vertx_version"]}")
    implementation("org.myddd.vertx:myddd-vertx-cache-api:${rootProject.extra["myddd_vertx_version"]}")
    //依赖注入实现（仅测试下）
    testImplementation(project(":myddd-gs-infra"))
    testImplementation("org.myddd.vertx:myddd-vertx-cache-sharedata:${rootProject.extra["myddd_vertx_version"]}")
    testImplementation("org.myddd.vertx:myddd-vertx-querychannel-hibernate:${rootProject.extra["myddd_vertx_version"]}")
}
