package com.luukitoo.chat.presentation.screen.chat.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.luukitoo.chat.core.extension.toTimeString
import com.luukitoo.chat.domain.model.Message
import com.luukitoo.chat.presentation.ui.theme.ChatTheme
import java.util.UUID

@Composable
fun MessageBubble(
    message: Message,
    fromCurrentUser: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (fromCurrentUser) {
            Arrangement.End
        } else {
            Arrangement.Start
        }
    ) {
        Column(
            horizontalAlignment = if (fromCurrentUser) {
                Alignment.End
            } else {
                Alignment.Start
            }
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = message.senderUsername ?: "",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier
                    .background(
                        color = if (fromCurrentUser) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.secondaryContainer
                        },
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(
                        horizontal = 12.dp,
                        vertical = 6.dp
                    ),
                text = message.text ?: "",
                color = if (fromCurrentUser) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.onSecondaryContainer
                }
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = message.time?.toTimeString() ?: "",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 12.sp
            )
        }
    }
}

@Preview
@Composable
fun MessageBubblePreview() {
    ChatTheme {
        Box(Modifier.background(MaterialTheme.colorScheme.background)) {
            MessageBubble(
                message = Message(
                    id = UUID.randomUUID().toString(),
                    text = "Hello there!",
                    senderId = UUID.randomUUID().toString(),
                    senderUsername = "luukitoo_",
                    time = System.currentTimeMillis()
                ),
                fromCurrentUser = true
            )
        }
    }
}