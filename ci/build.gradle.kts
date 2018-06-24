
open class SchematicExtension {

}

extensions.create("worksSchematic", SchematicExtension::class.java)
fun org.gradle.api.Project.`worksSchematic`(configure: SchematicExtension.() -> Unit): Unit =
        (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("worksSchematic", configure)

worksSchematic {

}
