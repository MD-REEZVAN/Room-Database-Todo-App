package com.reezvan.todoapp.appNavigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.reezvan.todoapp.presentation.HomeScreen
import com.reezvan.todoapp.presentation.SplashScreen
import com.reezvan.todoapp.presentation.WelcomeScreen
import com.reezvan.todoapp.viewModel.TodoViewModel


@Composable
fun AppNavigationCenter(modifier:Modifier= Modifier, todoViewModel: TodoViewModel) {

    val navController= rememberNavController()

    NavHost(navController = navController, startDestination = Routes.splashScreen) {

        composable<Routes.splashScreen>{
            SplashScreen(navController = navController)
        }

        composable<Routes.welcomeScreen> {
            WelcomeScreen(navController=navController)
        }

        composable<Routes.homeScreen> {
            HomeScreen(navController = navController,todoViewModel=todoViewModel)
        }
    }
}