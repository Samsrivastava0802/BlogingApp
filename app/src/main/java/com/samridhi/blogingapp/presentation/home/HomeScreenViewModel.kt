package com.samridhi.blogingapp.presentation.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samridhi.blogingapp.domain.FetchPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val fetchPostUseCase: FetchPostUseCase,
) : ViewModel() {
    var uiState by mutableStateOf(HomeScreenUiState())
        private set

    var uiSideEffect by mutableStateOf<HomeScreenUiSideEffect>(
        HomeScreenUiSideEffect.NoEffect
    )
        private set

    private var currentPage = 1

    init {
        fetchPost(currentPage)
    }


    private fun fetchPost(page: Int, perPage: Int = 10, isLoadMore: Boolean = false) {
        viewModelScope.launch {
            val response = fetchPostUseCase.invoke(perPage = perPage, page = page)
            if (response.isSuccessful) {
                response.body()?.let { responseBody ->
                    val blogList = responseBody.map {
                        BlogInfo(
                            id = it.id?.toString() ?: "",
                            title = it.title?.rendered ?: "",
                            author = it.author,
                            status = it.status ?: "",
                            commentStatus = it.comment_status ?: "",
                            link = it.link ?: ""
                        )
                    }
                    // Added manual delay to see the shimmer
                    if (isLoadMore) delay(600)
                    uiState = uiState.copy(
                        items = if (isLoadMore) uiState.items + blogList else blogList,
                        isLoadingMore = false,
                        screenState = ScreenState.DEFAULT
                    )
                }
            }
        }
    }

    fun onEvent(event: HomeScreenUiEvent) {
        when (event) {
            is HomeScreenUiEvent.OnClickBlogItem -> {
                uiSideEffect = HomeScreenUiSideEffect.OpenBlogDetailScreen(event.link)
            }

            HomeScreenUiEvent.OnLoadMore -> {
                uiState = uiState.copy(isLoadingMore = true)
                fetchPost(currentPage + 1, isLoadMore = true)
            }
        }
    }


    fun resetSideEffects() {
        uiSideEffect = HomeScreenUiSideEffect.NoEffect
    }
}

data class HomeScreenUiState(
    val screenState: ScreenState = ScreenState.LOADING,
    val items: List<BlogInfo> = emptyList(),
    val isLoadingMore: Boolean = false,
)

data class BlogInfo(
    val title: String = "",
    val id: String = "",
    val author: Int?,
    val status: String = "",
    val commentStatus: String = "",
    val link: String,
)

enum class ScreenState {
    LOADING,
    EMPTY,
    DEFAULT
}

sealed class HomeScreenUiEvent {
    data class OnClickBlogItem(val link: String) : HomeScreenUiEvent()
    data object OnLoadMore : HomeScreenUiEvent()
}

sealed class HomeScreenUiSideEffect {
    data object NoEffect : HomeScreenUiSideEffect()
    data class OpenBlogDetailScreen(val url: String) : HomeScreenUiSideEffect()
}