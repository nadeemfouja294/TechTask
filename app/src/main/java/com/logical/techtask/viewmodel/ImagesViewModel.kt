package com.logical.techtask.viewmodel

import android.content.res.Resources
import androidx.lifecycle.viewModelScope
import com.logical.domain.common.Resource
import com.logical.domain.usecases.ImagesUseCase
import com.logical.techtask.R
import com.logical.techtask.mapper.ImageDetailsDomainToPresentationMapper
import com.logical.techtask.mapper.ImageDetailsPresentationToUiMapper
import com.logical.techtask.model.ImagesViewState
import com.logical.techtask.model.emptyViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @constructor [ImagesUseCase], [ImageDetailsDomainToPresentationMapper], [Resources], [ImageDetailsPresentationToUiMapper]
 * This class consumes [ImagesUseCase] and updates the [ImagesViewState] accordingly.
 */
@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val imagesUseCase: ImagesUseCase,
    private val imageDetailsDomainToPresentation: ImageDetailsDomainToPresentationMapper,
    private val resources: Resources,
    val imageDetailsToUiMapper: ImageDetailsPresentationToUiMapper
) : BaseViewModel<ImagesViewState>() {

    /**
     * This function initiates the [ImagesViewState]
     */
    override fun initialState() = emptyViewState()

    private val _searchText = MutableStateFlow("cats")
    val searchText = _searchText.asStateFlow()

    init {
        setDataLoading()
    }

    /**
     * This function invokes the [ImagesUseCase] and updates the [ImagesViewState] accordingly.
     */
    fun getImages(query: String) {
        setDataLoading()
        viewModelScope.launch {
            imagesUseCase(query).collect { imagesResource ->
                when (imagesResource) {
                    is Resource.Success -> {
                        updateState { currentViewState ->
                            currentViewState.copy(
                                isDataLoading = false,
                                images = imagesResource.data?.map { imageDetailsDomain ->
                                    imageDetailsDomainToPresentation.toPresentation(
                                        imageDetailsDomain
                                    )
                                } ?: emptyList()
                            )
                        }
                    }

                    is Resource.Error -> {
                        updateState { currentViewState ->
                            currentViewState.copy(
                                isDataLoading = false,
                                errorMessage = imagesResource.message
                                    ?: resources.getString(R.string.something_went_wrong)
                            )
                        }
                    }
                }
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    private fun setDataLoading() {
        updateState { currentViewState -> currentViewState.copy(isDataLoading = true) }
    }
}