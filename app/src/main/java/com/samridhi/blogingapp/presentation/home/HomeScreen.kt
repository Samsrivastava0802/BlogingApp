package com.samridhi.blogingapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.samridhi.blogingapp.alias.AppString
import com.samridhi.blogingapp.navigation.HomeScreenActions
import com.samridhi.blogingapp.presentation.common.ErrorMessage
import com.samridhi.blogingapp.ui.theme.darkBlack
import com.samridhi.blogingapp.ui.theme.gray70
import com.samridhi.blogingapp.ui.theme.ht1
import com.samridhi.blogingapp.ui.theme.ht2
import com.samridhi.blogingapp.ui.theme.lightBlack
import com.samridhi.blogingapp.ui.theme.subTitle
import com.samridhi.blogingapp.utils.isScrolledToTheEnd
import com.valentinilk.shimmer.shimmer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onAction: (homeScreenActions: HomeScreenActions) -> Unit,
) {
    when (viewModel.uiState.screenState) {
        ScreenState.LOADING -> {
            Column(
                modifier = Modifier.shimmer()
            ) {
                repeat(20) {
                    ShimmerItem()
                }
            }
        }

        ScreenState.EMPTY -> {
            ErrorMessage(errorMessage = stringResource(id = AppString.no_data_available))
        }

        else -> {
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Text(
                            modifier = Modifier.clickable {
                                viewModel.onEvent(HomeScreenUiEvent.OnLoadMore)
                            },
                            text = stringResource(AppString.Blogs),
                            style = MaterialTheme.typography.ht2.copy(
                                fontSize = 20.sp, color = darkBlack
                            )
                        )
                    }
                    )
                }) { innerPadding ->
                HomeScreenContent(
                    modifier = Modifier.padding(innerPadding),
                    uiState = viewModel.uiState,
                    onEvent = viewModel::onEvent
                )
            }
        }
    }

    LaunchedEffect(key1 = viewModel.uiSideEffect) {
        handleSideEffect(
            onAction = onAction,
            effect = viewModel.uiSideEffect
        )
        viewModel.resetSideEffects()
    }
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    uiState: HomeScreenUiState,
    onEvent: (HomeScreenUiEvent) -> Unit,
) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = modifier
    ) {
        items(uiState.items) { it ->
            BlogItem(
                data = it,
                onClick = {
                    onEvent(HomeScreenUiEvent.OnClickBlogItem(it))
                }
            )
        }
        item {
            if (uiState.isLoadingMore) {
                ShimmerItem()
            }
        }
    }
    if (listState.isScrolledToTheEnd()) {
        onEvent(HomeScreenUiEvent.OnLoadMore)
    }

}

@Composable
fun BlogItem(
    data: BlogInfo,
    onClick: (String) -> Unit,
) {
    Column(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth()
        .shadow(
            elevation = 8.dp, shape = RoundedCornerShape(8.dp)
        )
        .background(color = Color.White)
        .clickable {
            onClick(data.link)
        }
        .padding(16.dp)

    ) {
        Text(
            text = data.title,
            style = MaterialTheme.typography.ht1.copy(color = lightBlack, fontSize = 12.sp)
        )
        Spacer(modifier = Modifier.size(24.dp))
        Text(text = buildAnnotatedString {
            withStyle(
                style = MaterialTheme.typography.subTitle.copy(
                    fontSize = 10.sp, color = gray70
                ).toSpanStyle()
            ) {
                append(stringResource(AppString.author))
                append("-")
            }
            withStyle(
                style = MaterialTheme.typography.ht2.copy(
                    fontSize = 10.sp, color = lightBlack
                ).toSpanStyle()
            ) {
                append(data.author.toString())
            }
        })
        Spacer(modifier = Modifier.size(24.dp))
        Text(text = buildAnnotatedString {
            withStyle(
                style = MaterialTheme.typography.subTitle.copy(
                    fontSize = 10.sp, color = gray70
                ).toSpanStyle()
            ) {
                append(stringResource(AppString.status))
                append("-")
            }
            withStyle(
                style = MaterialTheme.typography.ht2.copy(
                    fontSize = 10.sp, color = lightBlack
                ).toSpanStyle()
            ) {
                append(data.status)
            }
        })
        Spacer(modifier = Modifier.size(24.dp))
        Text(text = buildAnnotatedString {
            withStyle(
                style = MaterialTheme.typography.subTitle.copy(
                    fontSize = 10.sp, color = gray70
                ).toSpanStyle()
            ) {
                append(stringResource(AppString.commentStatus))
                append("-")
            }
            withStyle(
                style = MaterialTheme.typography.ht2.copy(
                    fontSize = 10.sp, color = lightBlack
                ).toSpanStyle()
            ) {
                append(data.commentStatus)
            }
        })
    }
}

@Composable
fun ShimmerItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp))
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(16.dp)
            .shimmer()
    ) {
        // Title placeholder
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(16.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.Gray)
        )

        Spacer(modifier = Modifier.height(24.dp))
        // Author Row
        Row {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(10.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(10.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Gray)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Status Row
        Row {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(10.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(10.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Gray)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        // Comment Status Row
        Row {
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(10.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(10.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Gray)
            )
        }
    }
}

fun handleSideEffect(
    onAction: (HomeScreenActions) -> Unit,
    effect: HomeScreenUiSideEffect,
) {
    when (effect) {
        HomeScreenUiSideEffect.NoEffect -> {
// stub
        }

        is HomeScreenUiSideEffect.OpenBlogDetailScreen -> {
            onAction(HomeScreenActions.OpenBrowserScreen(effect.url))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(AppString.Blogs),
                    style = MaterialTheme.typography.ht2.copy(
                        fontSize = 20.sp, color = darkBlack
                    )
                )
            }
            )
        }) { innerPadding ->
        HomeScreenContent(
            modifier = Modifier.padding(innerPadding),
            uiState = HomeScreenUiState(),
            onEvent = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShimmerItemPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ShimmerItem()
    }
}

