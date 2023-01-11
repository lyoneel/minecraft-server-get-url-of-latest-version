import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.4.30"
}

group = "com.lyoneel"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(group = "org.json", name = "json", version = "20201115")
    testCompileOnly("junit", "junit", "4.12")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.useIR = true


tasks.withType<Jar>() {
    manifest {
        attributes["Main-Class"] = "com.lyoneel.MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
}
