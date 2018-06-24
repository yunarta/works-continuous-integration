package com.mobilesolutionworks.ci.core

import com.mobilesolutionworks.ci.core.model.SchematicExtension
import org.gradle.api.Plugin
import org.gradle.api.Project


class CorePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            extensions.create("worksSchematic", SchematicExtension::class.java)
        }
    }
}