package com.dibgiorgi.sapientai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dibgiorgi.sapientai.ui.composables.AIChatMessage
import com.dibgiorgi.sapientai.ui.composables.AppBottomBar
import com.dibgiorgi.sapientai.ui.composables.AppTopBar
import com.dibgiorgi.sapientai.ui.composables.ChatMessage
import com.dibgiorgi.sapientai.ui.composables.UserChatMessage
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
                    LazyColumn(
                        Modifier
                            .padding(innerPadding)
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            AIChatMessage(message = stringResource(R.string.home_intro_message))
                        }
                        
                        item { 
                            UserChatMessage(message = "Test message")
                        }
                    }
                }
            }
        }
    }
}

