package com.reezvan.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.reezvan.todoapp.appNavigation.AppNavigationCenter
import com.reezvan.todoapp.ui.theme.TodoAppTheme
import com.reezvan.todoapp.viewModel.TodoViewModel

class MainActivity : ComponentActivity() {
    val todoViewModel: TodoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigationCenter(modifier = Modifier.padding(innerPadding), todoViewModel = todoViewModel)
                }
            }
        }
    }
}
