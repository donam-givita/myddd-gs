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
}