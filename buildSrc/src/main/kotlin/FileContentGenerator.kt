object FileContentGenerator {
    fun sharedCommonComposeUiModelContent(
        packageName: String,
        screenName: String,
    ): String =
        """
            package $packageName
            
            data class ${screenName}UiModel(
                val title: String,
            )
        """.trimIndent()

    fun sharedCommonComposeViewModelContent(
        packageName: String,
        screenName: String,
    ): String =
        """
            package $packageName

            import com.compose.screengenerator.util.CommonFlow
            import com.compose.screengenerator.util.toCommonFlow
            import com.compose.screengenerator.util.toCommonMutableStateFlow
            import kotlinx.coroutines.flow.MutableStateFlow

            class ${screenName}ViewModel {

                private val defaultUiModel = ${screenName}UiModel(
                    title = "${screenName}",
                )

                private val _uiData = MutableStateFlow(defaultUiModel).toCommonMutableStateFlow()

                val uiData: CommonFlow<${screenName}UiModel>
                    get() = _uiData.toCommonFlow()
            }

        """.trimIndent()

    fun sharedCommonComposeScreenContent(
        packageName: String,
        screenName: String,
    ): String =
        """
            package $packageName

            import androidx.compose.material3.CircularProgressIndicator
            import androidx.compose.material3.Text
            import androidx.compose.runtime.Composable
            import androidx.compose.runtime.collectAsState
            import androidx.compose.runtime.getValue

            @Composable
            fun ${screenName}Screen(
                viewModel: ${screenName}ViewModel,
            ) {
                val uiModelState = viewModel.uiData.collectAsState(null)

                when (val uiModel = uiModelState.value) {
                    null -> CircularProgressIndicator()
                    else -> Text(uiModel.title)
                }
            }

        """.trimIndent()

    fun sharedIosViewController(
        packageName: String,
        screenName: String,
    ): String =
        """
            package $packageName

            import androidx.compose.ui.window.ComposeUIViewController

            fun ${screenName}ViewController(
                viewModel: ${screenName}ViewModel,
            ) =
                ComposeUIViewController {
                    ${screenName}Screen(
                        viewModel = viewModel,
                    )
                }

        """.trimIndent()

    fun iosAppScreenView(
        screenName: String,
    ): String =
        """
            //
            //  ${screenName}.swift
            //  iosApp
            //
            //  Created by DELBUT Maxime (Prestataire) on 20/05/2023.
            //  Copyright © 2023 orgName. All rights reserved.
            //

            import Foundation
            import SwiftUI
            import shared

            struct ${screenName}ScreenCompose: UIViewControllerRepresentable {
                let viewModel: ${screenName}ViewModel
                
                init() {
                    self.viewModel = ${screenName}ViewModel()
                }
                
                func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
                
                func makeUIViewController(context: Context) -> some UIViewController {
                    ${screenName}ViewControllerKt.${screenName}ViewController(viewModel: viewModel)
                }
            }

            struct ${screenName}ScreenView: View {
                var body: some View {
                    ${screenName}ScreenCompose()
                }
            }

        """.trimIndent()
}
