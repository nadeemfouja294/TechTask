package com.logical.techtask.ui.mainlistscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.logical.techtask.R
import com.logical.techtask.model.ImageDetailsUiModel
import com.logical.techtask.ui.component.NoDataWidget
import com.logical.techtask.ui.component.SearchBar
import com.logical.techtask.ui.component.TryAgainWidget
import com.logical.techtask.ui.navigation.Screens
import com.logical.techtask.ui.navigation.Screens.Companion.IMAGE_ID
import com.logical.techtask.viewmodel.ImagesViewModel

/**
 * This is the main composable for the list screen.
 *
 * @param navController The navigation controller used to navigate to other screens.
 * @param viewModel The view model used for managing the state of the list screen.
 */
@ExperimentalComposeUiApi
@ExperimentalLayoutApi
@Composable
fun ListScreen(
    navController: NavController,
    viewModel: ImagesViewModel = hiltViewModel()
) {
    val state = viewModel.currentViewState()
    val isShowDialog: MutableState<Boolean> = remember { mutableStateOf(false) }
    val imageDetail: MutableState<ImageDetailsUiModel?> = remember { mutableStateOf(null) }
    val searchText by viewModel.searchText.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getImages(viewModel.searchText.value)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                backgroundColor = MaterialTheme.colors.primary
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            val softKeyboardController = LocalSoftwareKeyboardController.current

            SearchBar(
                value = searchText,
                onSearch = { searchQuery ->
                    viewModel.getImages(searchQuery)
                    softKeyboardController?.hide()
                },
                onValueChange = viewModel::onSearchTextChange
            )

            when {
                state.isDataLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = MaterialTheme.colors.onBackground)
                    }
                }

                state.errorMessage.isNotBlank() -> {
                    TryAgainWidget(state.errorMessage) { viewModel.getImages(viewModel.searchText.value) }
                }

                state.images.isEmpty() -> {
                    NoDataWidget()
                }

                else -> {
                    Listing(
                        modifier = Modifier,
                        list = state.images.map { image ->
                            viewModel.imageDetailsToUiMapper.toUi(
                                image
                            )
                        }
                    ) { imageData ->
                        imageDetail.value = imageData
                        isShowDialog.value = true
                    }
                }
            }

            if (isShowDialog.value) {
                imageDetail.value?.let { imageDetail ->
                    DetailScreenWarningDialog(
                        openDialog = isShowDialog,
                        imageDetail = imageDetail,
                        onPositive = {
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                IMAGE_ID,
                                imageDetail
                            )
                            navController.navigate(Screens.ImageDetail.route)
                            isShowDialog.value = false
                        },
                        onNegative = { isShowDialog.value = false }
                    )
                }
            }
        }
    }
}