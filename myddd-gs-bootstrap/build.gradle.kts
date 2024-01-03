plugins {
    java
    application
    kotlin("jvm")
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "dev.donam.exr"
version = rootProject.extra["version"]!!

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

val mainVerticleName = "dev.donam.exr.bootstrap.MyBootstrapVerticle"
val launcherClassName = "io.vertx.core.Launcher"

application {
    mainClass.set(launcherClassName)
}


tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveClassifier.set("fat")
    manifest {
        attributes(mapOf("Main-Verticle" to mainVerticleName))
    }
    mergeServiceFiles()
}

dependencies {
    implementation("io.vertx:vertx-web-client:${rootProject.extra["vertx_version"]}")
    implementation("org.myddd.vertx:myddd-vertx-web:${rootProject.extra["myddd_vertx_version"]}")
    implementation(project(":myddd-gs-domain"))
    implementation(project(":myddd-gs-api"))
    implementation(project(":myddd-gs-application"))
    implementation(project(":myddd-gs-infra"))
 
    api("org.myddd.vertx:myddd-vertx-base-api:${rootProject.extra["myddd_vertx_version"]}")
    api("org.myddd.vertx:myddd-vertx-ioc-api:${rootProject.extra["myddd_vertx_version"]}")
    api("org.myddd.vertx:myddd-vertx-i18n-api:${rootProject.extra["myddd_vertx_version"]}")
    api("org.myddd.vertx:myddd-vertx-cache-api:${rootProject.extra["myddd_vertx_version"]}")
    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation("io.vertx:vertx-pg-client:${rootProject.extra["vertx_version"]}")

    implementation("org.myddd.vertx:myddd-vertx-i18n-provider:${rootProject.extra["myddd_vertx_version"]}")
    implementation("org.myddd.vertx:myddd-vertx-base-provider:${rootProject.extra["myddd_vertx_version"]}")
    implementation("org.myddd.vertx:myddd-vertx-cache-sharedata:${rootProject.extra["myddd_vertx_version"]}")
    implementation("org.myddd.vertx:myddd-vertx-repository-hibernate:${rootProject.extra["myddd_vertx_version"]}")
    implementation("org.myddd.vertx:myddd-vertx-querychannel-hibernate:${rootProject.extra["myddd_vertx_version"]}")

    implementation("io.vertx:vertx-json-schema:${rootProject.extra["vertx_version"]}")
}
