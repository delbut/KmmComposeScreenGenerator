object OutputFile {
    val SHARED_COMMON_COMPOSE_UI_MODEL = OutputFileProperties(
        filePrefix = "UiModel",
        path = "/shared/src/commonMain/kotlin/com/compose/screengenerator/screen/",
        packageName = "com.compose.screengenerator.screen",
        extension = ".kt",
    )
    val SHARED_COMMON_COMPOSE_VIEW_MODEL = OutputFileProperties(
        filePrefix = "ViewModel",
        path = "/shared/src/commonMain/kotlin/com/compose/screengenerator/screen/",
        packageName = "com.compose.screengenerator.screen",
        extension = ".kt",
    )
    val SHARED_COMMON_COMPOSE_SCREEN = OutputFileProperties(
        filePrefix = "Screen",
        path = "/shared/src/commonMain/kotlin/com/compose/screengenerator/screen/",
        packageName = "com.compose.screengenerator.screen",
        extension = ".kt",
    )
    val SHARED_IOS_COMPOSE_SCREEN = OutputFileProperties(
        filePrefix = "ViewController",
        path = "/shared/src/iosMain/kotlin/com/compose/screengenerator/screen/",
        packageName = "com.compose.screengenerator.screen",
        extension = ".kt",
    )
    val IOS_APP_SCREEN_VIEW = OutputFileProperties(
        filePrefix = "ScreenView",
        path = "/iosApp/iosApp/screen/",
        packageName = "",
        extension = ".swift",
    )
}

data class OutputFileProperties(
    val filePrefix: String,
    val path: String,
    val packageName: String,
    val extension: String,
)
