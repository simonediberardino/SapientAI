package com.dibgiorgi.sapientai.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dibgiorgi.sapientai.R
import com.dibgiorgi.sapientai.ui.theme.SapientAITheme
import com.dibgiorgi.sapientai.ui.theme.Title

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
fun AppBottomBar() {
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
            focusedContainerColor = MaterialTheme.colorScheme.tertiary,
            unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
        ),
        value = "",
        onValueChange = {},
        trailingIcon = {
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_camera_alt_24),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
                )

                Image(
                    painter = painterResource(id = R.drawable.baseline_mic_none_24),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
                )
            }
        }
    )
}

@Composable
fun ChatMessage(
    modifier: Modifier = Modifier,
    author: String,
    message: String
) {
    Text(
        modifier = modifier,
        text = "$author: $message"
    )
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