package com.luukitoo.chat.presentation.screen.chat.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luukitoo.chat.presentation.ui.theme.ChatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagingBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onSend: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = "Message") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Send
            ),
            keyboardActions = KeyboardActions(
                onSend = { onSend(value) }
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        FloatingActionButton(
            onClick = { onSend(value) },
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 0.dp
            )
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun MessagingBarPreview() {
    ChatTheme {
        MessagingBar(
            value = "",
            onValueChange = { },
            onSend = { }
        )
    }
}