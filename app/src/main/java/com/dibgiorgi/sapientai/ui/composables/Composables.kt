package com.dibgiorgi.sapientai.ui.composables

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dibgiorgi.sapientai.R
import com.dibgiorgi.sapientai.ui.theme.SapientAITheme
import com.dibgiorgi.sapientai.ui.theme.Title
import com.dibgiorgi.sapientai.ui.viewmodel.ChatViewModel

@Composable
fun AppTopBar(
    onBack: (() -> Unit)? = null
) {
    Column {
        Row(
            Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .clickable {
                        if (onBack != null) {
                            onBack()
                        }
                    }
                    .weight(1 / 10f),
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "",
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
            )

            Text(
                modifier = Modifier.weight(8 / 10f),
                text = "SapientAI",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                fontSize = Title
            )

            Image(
                modifier = Modifier
                    .weight(1 / 10f),
                painter = painterResource(id = R.drawable.baseline_list_24),
                contentDescription = "",
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
            )
        }

        HorizontalDivider(color = MaterialTheme.colorScheme.primary)
    }

}

@Composable
fun AppBottomBar(
    viewModel: ChatViewModel? = null
) {
    val input = remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    TextField(
        placeholder = {
            Text(
                text = stringResource(R.string.ask_me_something),
                color = MaterialTheme.colorScheme.onTertiary,
                fontWeight = FontWeight.Light
            )
        },
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onTertiary,
            focusedContainerColor = MaterialTheme.colorScheme.tertiary,
            unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
        ),
        value = input.value,
        onValueChange = { text ->
            input.value = text
        },
        trailingIcon = {
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            Toast.makeText(context, "Camera", Toast.LENGTH_SHORT).show()
                        },
                    painter = painterResource(id = R.drawable.baseline_camera_alt_24),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
                )

                Image(
                    modifier = Modifier.size(32.dp).clickable {
                        Toast.makeText(context, "Microphone", Toast.LENGTH_SHORT).show()
                    },
                    painter = painterResource(id = R.drawable.baseline_mic_none_24),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
                )

                Image(
                    modifier = Modifier.size(32.dp).clickable {
                        viewModel?.input(input.value)
                    },
                    painter = painterResource(id = R.drawable.baseline_arrow_circle_right_24),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
                )
            }
        }
    )
}


@Composable
fun AIChatMessage(
    message: String
) {
    ChatMessage(
        modifier = Modifier
            .fillMaxWidth(4 / 5f),
        author = stringResource(id = R.string.app_name),
        message = message,
        horizontal = Arrangement.Start
    )
}

@Composable
fun UserChatMessage(
    message: String
) {
    ChatMessage(
        modifier = Modifier
            .fillMaxWidth(4 / 5f),
        author = stringResource(id = R.string.you),
        message = message,
        horizontal = Arrangement.End
    )
}

@Composable
fun ChatMessage(
    modifier: Modifier = Modifier,
    author: String,
    message: String,
    horizontal: Arrangement.Horizontal
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = horizontal
    ) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(16.dp)
        )
        {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("$author\n")
                    }
                    append(message)
                }
            )
        }
    }
}

@Preview
@Composable
fun AppTopBarPreview() {
    SapientAITheme {
        AppTopBar()
    }
}

@Preview
@Composable
fun AppBottomBarPreview() {
    SapientAITheme {
        AppBottomBar()
    }
}

@Preview()
@Composable
fun ChatMessagePreview() {
    SapientAITheme {
        ChatMessage(author = "SapientAI", message = "Text Message", horizontal = Arrangement.Start)
    }
}
