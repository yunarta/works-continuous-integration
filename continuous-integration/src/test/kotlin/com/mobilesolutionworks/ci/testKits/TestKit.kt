package com.mobilesolutionworks.ci.testKits

import org.gradle.internal.impldep.org.apache.commons.io.FileUtils
import org.junit.Before
import java.io.File
import java.nio.file.Paths
import java.util.*

class CopyResourceFolder(parent: File, private val resource: String) {



    init {

    }

    fun create() {

    }
}

abstract class TestKit(resourcePath: String) {

    val rootDir by lazy {
        val root = File(Paths.get("build", "tmp", "testKit", javaClass.simpleName).toFile(), resourcePath)
        root.mkdirs()

        FileUtils.copyDirectory(File(javaClass.classLoader.getResource(resourcePath).file), root)
        root
    }

    @Before
    fun createJavaAgent() {
        javaClass.classLoader.getResourceAsStream("javaagent-for-testkit.properties")?.let {
            Properties().apply {
                load(it)
            }.let {
                val agentPath = it.getProperty("agentPath")
                val outputDir = it.getProperty("outputDir")

                val execFile = File(outputDir, "${javaClass.name}.exec")
                val agentString = "org.gradle.jvmargs=-javaagent\\:${agentPath}\\=destfile\\=${execFile.absolutePath}"

                File(rootDir, "gradle.properties").writeText(agentString)
            }
        }
    }
}