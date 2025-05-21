package com.samridhi.blogingapp.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {
    var uiState by mutableStateOf(HomeScreenUiState())
        private set

    var uiSideEffect by mutableStateOf<HomeScreenUiSideEffect>(
        HomeScreenUiSideEffect.NoEffect
    )
        private set
init {
    hitApi()
}
    private fun hitApi() {
      uiState = uiState.copy(
          items = listOf(
              BlogInfo(
                  id = "1",
                  title = "The Spending Trap Smart Indians Are Avoiding: Diderot Effect",
                  author = 223152417,
                  status = "publish",
                  commentStatus = "closed"
              ),
              BlogInfo(
                  id = "2",
                  title = "Smart Indians",
                  author = 223152417,
                  status = "publish",
                  commentStatus = "closed"
              ),
              BlogInfo(
                  id = "3",
                  title = "Spending Trap",
                  author = 223152417,
                  status = "publish",
                  commentStatus = "closed"
              ),
              BlogInfo(
                  id = "4",
                  title = "Indians Effect",
                  author = 223152417,
                  status = "publish",
                  commentStatus = "closed"
              ),
              BlogInfo(
                  id = "5",
                  title = "Effect",
                  author = 223152417,
                  status = "publish",
                  commentStatus = "closed"
              ),
              BlogInfo(
                  id = "6",
                  title = "Diderot Effect",
                  author = 223152417,
                  status = "publish",
                  commentStatus = "closed"
              ),
              BlogInfo(
                  id = "7",
                  title = "Indians Are Avoiding: Diderot Effect",
                  author = 223152417,
                  status = "publish",
                  commentStatus = "closed"
              ),
              BlogInfo(
                  id = "8",
                  title = "The Spending Indians Are Avoiding: Diderot Effect",
                  author = 223152410,
                  status = "publish",
                  commentStatus = "closed"
              ),
              BlogInfo(
                  id = "9",
                  title = "The Spending Trap Smart Indians",
                  author = 22315879,
                  status = "publish",
                  commentStatus = "closed"
              ),
              BlogInfo(
                  id = "10",
                  title = "Fund of Funds",
                  author = 223152417,
                  status = "publish",
                  commentStatus = "closed"
              )
          )
      )
    }

    fun onEvent(event: HomeScreenUiEvent) {
        when (event) {
            is HomeScreenUiEvent.OnClickBlogItem -> {
                uiSideEffect = HomeScreenUiSideEffect.OpenBlogDetailScreen(event.id)
            }
        }
    }

    fun resetSideEffects() {
        uiSideEffect = HomeScreenUiSideEffect.NoEffect
    }

}

data class HomeScreenUiState(
    val items: List<BlogInfo> = emptyList(),
)
data class BlogInfo(
    val title: String = "",
    val id: String = "",
    val author: Int = 1,
    val status: String = "",
    val commentStatus: String = "",
)

sealed class HomeScreenUiEvent {
    data class OnClickBlogItem(val id: String) : HomeScreenUiEvent()
}

sealed class HomeScreenUiSideEffect {
    data object NoEffect : HomeScreenUiSideEffect()
    data class OpenBlogDetailScreen(val id: String) : HomeScreenUiSideEffect()
}