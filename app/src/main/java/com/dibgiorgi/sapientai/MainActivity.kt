package com.dibgiorgi.sapientai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dibgiorgi.sapientai.ui.composables.AppBottomBar
import com.dibgiorgi.sapientai.ui.composables.AppTopBar
import com.dibgiorgi.sapientai.ui.composables.ChatMessage
import com.dibgiorgi.sapientai.ui.theme.SapientAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SapientAITheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { AppTopBar() },
                    bottomBar = { AppBottomBar()}
                ) { innerPadding ->
                    ChatMessage(
                        modifier = Modifier.padding(innerPadding).padding(16.dp),
                        author = stringResource(id = R.string.app_name),
                        message = stringResource(R.string.home_intro_message)
                    )
                }
            }
        }
    }
}

