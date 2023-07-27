package com.luukitoo.chat.presentation.screen.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.luukitoo.chat.core.extension.launchSingle
import com.luukitoo.chat.domain.model.User
import com.luukitoo.chat.presentation.navigation.NavDestinations
import com.luukitoo.chat.presentation.screen.chat.component.MessageBubble
import com.luukitoo.chat.presentation.screen.chat.component.MessagingBar
import com.luukitoo.chat.presentation.screen.chat.viewmodel.ChatEvent
import com.luukitoo.chat.presentation.screen.chat.viewmodel.ChatViewState
import com.luukitoo.chat.presentation.ui.theme.ChatTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    state: ChatViewState,
    onEvent: (ChatEvent) -> Unit,
    navController: NavController,
) {

    var showLogOutDialog by remember {
        mutableStateOf(false)
    }

    val chatScrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    state.newMessageAppeared?.get()?.let {
        if (chatScrollState.firstVisibleItemIndex == 0) {
            scope.launch {
                chatScrollState.animateScrollToItem(0)
            }
        }
    }

    if (showLogOutDialog) {
        AlertDialog(
            onDismissRequest = { showLogOutDialog = false },
            text = { Text(text = "You really want to log out?") },
            confirmButton = {
                TextButton(onClick = { onEvent(ChatEvent.LogOut) }) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogOutDialog = false }) {
                    Text(text = "No")
                }
            }
        )
    }

    state.logOut?.get()?.let {
        navController.launchSingle(NavDestinations.REGISTRATION)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.shadow(16.dp),
                title = { Text(text = state.userInfo.username ?: "") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
                actions = {
                    IconButton(
                        onClick = { showLogOutDialog = true }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ExitToApp,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                reverseLayout = true,
                state = chatScrollState
            ) {
                items(state.messages.reversed()) { message ->
                    MessageBubble(
                        message = message,
                        fromCurrentUser = state.userInfo.id == message.senderId
                    )
                }
            }
            MessagingBar(
                value = state.sendingText,
                onValueChange = {
                    onEvent(ChatEvent.UpdateSendingText(it))
                },
                onSend = {
                    onEvent(ChatEvent.SendMessage)
                }
            )
        }
    }
}

@Preview
@Composable
fun ChatScreenPreview() {
    ChatTheme {
        ChatScreen(
            state = ChatViewState(
                userInfo = User(username = "luukitoo_")
            ),
            onEvent = { },
            navController = rememberNavController()
        )
    }
}