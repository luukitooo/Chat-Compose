package com.luukitoo.chat.core.extension

import androidx.navigation.NavController

fun NavController.launchSingle(route: String) {
    navigate(route) {
        popUpTo(graph.id) {
            inclusive = true
        }
    }
}