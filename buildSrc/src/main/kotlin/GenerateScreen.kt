import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class GenerateScreen : DefaultTask() {

    @Internal
    val SCREEN_NAME = "Home"

    @TaskAction
    fun generate() {
        println("generate")
        val screenDir = SCREEN_NAME.lowercase()
        createFile(
            properties = OutputFile.SHARED_COMMON_COMPOSE_UI_MODEL,
            project = project,
            screenDir = screenDir,
            content = { screenName, packageName ->
                FileContentGenerator.sharedCommonComposeUiModelContent(packageName, screenName)
            },
        )
        createFile(
            properties = OutputFile.SHARED_COMMON_COMPOSE_VIEW_MODEL,
            project = project,
            screenDir = screenDir,
            content = { screenName, packageName ->
                FileContentGenerator.sharedCommonComposeViewModelContent(packageName, screenName)
            },
        )
        createFile(
            properties = OutputFile.SHARED_COMMON_COMPOSE_SCREEN,
            project = project,
            screenDir = screenDir,
            content = { screenName, packageName ->
                FileContentGenerator.sharedCommonComposeScreenContent(packageName, screenName)
            },
        )
        createFile(
            properties = OutputFile.SHARED_IOS_COMPOSE_SCREEN,
            project = project,
            screenDir = screenDir,
            content = { screenName, packageName ->
                FileContentGenerator.sharedIosViewController(packageName, screenName)
            },
        )
        createFile(
            properties = OutputFile.IOS_APP_SCREEN_VIEW,
            project = project,
            screenDir = null,
            content = { screenName, _ ->
                FileContentGenerator.iosAppScreenView(screenName = screenName)
            },
        )
    }

    private fun createFile(
        properties: OutputFileProperties,
        screenDir: String?,
        project: Project,
        content: (screenName: String, packageName: String) -> String,
    ) {
        val packageName = when (screenDir) {
            null -> properties.packageName
            else -> properties.packageName + ".$screenDir"
        }
        val filePath =
            when (screenDir) {
                null -> properties.path
                else -> properties.path + screenDir
            }
        val fileName = SCREEN_NAME + properties.filePrefix

        val outputDirectory = File(project.rootDir, filePath)
        when (outputDirectory.exists()) {
            true -> {
            }

            false -> outputDirectory.mkdirs()
        }

        val file = File(outputDirectory, fileName + properties.extension)
        file.createNewFile()
        file.printWriter().use { out -> out.println(content(SCREEN_NAME, packageName)) }
    }
}
