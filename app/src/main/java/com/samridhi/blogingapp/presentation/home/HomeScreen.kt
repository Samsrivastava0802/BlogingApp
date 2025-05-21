package com.samridhi.blogingapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.samridhi.blogingapp.ui.theme.darkBlack
import com.samridhi.blogingapp.ui.theme.gray70
import com.samridhi.blogingapp.ui.theme.ht1
import com.samridhi.blogingapp.ui.theme.ht2
import com.samridhi.blogingapp.ui.theme.lightBlack
import com.samridhi.blogingapp.ui.theme.subTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    modifier = Modifier.clickable {
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

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    uiState: HomeScreenUiState,
    onEvent: (HomeScreenUiEvent) -> Unit,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(uiState.items){it->
            BlogItem(
                data = it,
                onClick = {
                    onEvent(HomeScreenUiEvent.OnClickBlogItem(it))
                }
            )
        }
    }

}

@Composable
fun BlogItem(
    data : BlogInfo,
    onClick: (String) -> Unit
) {
    Column(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth()
        .shadow(
            elevation = 8.dp, shape = RoundedCornerShape(8.dp)
        )
        .background(color = Color.White)
        .clickable {
            onClick(data.id)
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    modifier = Modifier.clickable {
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
            uiState = HomeScreenUiState(
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
            ),
            onEvent = {

            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        BlogItem(
            data = BlogInfo(
                title = "",
                status = "",
                commentStatus = "",
                author = 67,
                id = "1"
            ),
            onClick = {

            }
        )
    }
}