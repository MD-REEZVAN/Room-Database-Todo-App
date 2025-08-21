package com.reezvan.todoapp.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reezvan.todoapp.R
import com.reezvan.todoapp.appNavigation.Routes
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(modifier: Modifier = Modifier,navController: NavController) {
//Ye launched effect is composable ko bas ek bar run karwayega
    LaunchedEffect(Unit) {
//iska matlab ek second tak ye dikhega user ko
        delay(1000)
        navController.navigate(Routes.welcomeScreen){
            popUpTo(Routes.splashScreen){
                inclusive=true
            }
        }

    }



    Column(
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {



        Spacer(Modifier.height(8.dp))
        Text(
            text = "Todo App",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

    }
}