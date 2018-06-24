import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.2.50"
    `java-gradle-plugin`
    jacoco

    id("com.gradle.plugin-publish") version "0.9.10"
    id("com.mobilesolutionworks.gradle.jacoco") version "1.1.1"
}

group = "com.mobilesolutionworks.ci"
version = "1.0.0"

repositories {
    mavenCentral()
}

worksJacoco {
    hasTestKit = true
//    onlyRunCoverageWhenReporting = true
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    compileOnly(gradleApi())
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("commons-io:commons-io:2.6")

    testImplementation(gradleTestKit())
    testImplementation("junit:junit:4.12")
}

gradlePlugin {
    (plugins) {
        "works-continuous-integration" {
            id = "com.mobilesolutionworks.ci.core"
            implementationClass = "com.mobilesolutionworks.ci.core.CorePlugin"
        }
    }
}

pluginBundle {
    website = "https://github.com/yunarta/works-jacoco-gradle-plugin"
    vcsUrl = "https://github.com/yunarta/works-jacoco-gradle-plugin"
    description = "These are set of plugins that will automate continuous integration of Gradle project"
    tags = listOf("jacoco", "works")

    (plugins) {
        "works-continuous-integration" {
            id = "com.mobilesolutionworks.ci.core"
            displayName = """
                Core plugin

                This plugin should be installed in the Gradle root project
            """.trimIndent()
        }
    }
}

tasks.withType<Delete>().whenObjectAdded {
    if (name == "cleanTest") {
        delete(file("$buildDir/tmp/testKit"))
    }
}
