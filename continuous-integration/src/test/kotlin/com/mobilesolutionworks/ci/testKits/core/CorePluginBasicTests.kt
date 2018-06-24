package com.mobilesolutionworks.ci.testKits.core

import com.mobilesolutionworks.gradle.jacoco.TestKit
import org.gradle.testkit.runner.GradleRunner
import org.junit.Test

class CorePluginBasicTests : TestKit("gradle/Building-DSL") {

    @Test
    fun `test gradle extension structuring`() {
        GradleRunner.create()
                .forwardOutput()
                .withGradleVersion("3.3")
                .withPluginClasspath()
                .withProjectDir(rootDir)
                .withArguments("tasks")
                .build()
    }
}
