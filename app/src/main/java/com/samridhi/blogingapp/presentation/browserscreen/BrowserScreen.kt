package com.samridhi.blogingapp.presentation.browserscreen

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.samridhi.blogingapp.alias.AppString
import com.samridhi.blogingapp.navigation.BrowserScreenScreenActions
import com.samridhi.blogingapp.ui.theme.darkBlack
import com.samridhi.blogingapp.ui.theme.ht2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowserScreen(
    url: String,
    onAction: (BrowserScreenScreenActions) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(AppString.detail),
                        style = MaterialTheme.typography.ht2.copy(
                            fontSize = 20.sp, color = darkBlack
                        )
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onAction(BrowserScreenScreenActions.OnBack)
                        }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        BrowserScreenContent(
            modifier = Modifier.padding(innerPadding),
            url = url,
        )
    }
}

@Composable
fun BrowserScreenContent(
    modifier: Modifier = Modifier,
    url: String,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        AndroidView(
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    loadUrl(url)
                }
            }, update = {
                it.loadUrl(url)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BrowserScreenPreview() {
    BrowserScreen(url = "", onAction = {})
}