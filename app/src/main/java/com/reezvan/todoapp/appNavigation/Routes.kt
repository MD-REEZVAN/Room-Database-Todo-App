package com.reezvan.todoapp.appNavigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes{

    @Serializable
    data object splashScreen: Routes

    @Serializable
    data object welcomeScreen: Routes

    @Serializable
    data object homeScreen: Routes
}
