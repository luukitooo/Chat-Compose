package com.luukitoo.chat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.luukitoo.chat.core.extension.notNull
import com.luukitoo.chat.presentation.screen.chat.ChatScreen
import com.luukitoo.chat.presentation.screen.chat.viewmodel.ChatViewModel
import com.luukitoo.chat.presentation.screen.login.LoginScreen
import com.luukitoo.chat.presentation.screen.login.viewmodel.LoginViewModel
import com.luukitoo.chat.presentation.screen.registration.RegistrationScreen
import com.luukitoo.chat.presentation.screen.registration.viewmodel.RegistrationViewModel

@Composable
fun MainNavGraph() {

    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = if (FirebaseAuth.getInstance().currentUser.notNull()) {
            NavDestinations.CHAT
        } else {
            NavDestinations.REGISTRATION
        }
    ) {

        composable(
            route = NavDestinations.REGISTRATION
        ) {
            val registrationViewModel = hiltViewModel<RegistrationViewModel>()
            RegistrationScreen(
                state = registrationViewModel.viewState,
                onEvent = registrationViewModel::onEvent,
                navController = navHostController
            )
        }

        composable(NavDestinations.LOGIN) {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(
                state = loginViewModel.viewState,
                onEvent = loginViewModel::onEvent,
                navController = navHostController
            )
        }

        composable(NavDestinations.CHAT) {
            val chatViewModel = hiltViewModel<ChatViewModel>()
            ChatScreen(
                state = chatViewModel.viewState,
                onEvent = chatViewModel::onEvent,
                navController = navHostController
            )
        }
    }
}