package com.example.tweeter

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tweeter.api.TweeterApi
import com.example.tweeter.screens.CategoryScreen
import com.example.tweeter.screens.DetailScreen
import com.example.tweeter.ui.theme.TweeterTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
          TweeterTheme {
              Scaffold {

                  Box(modifier = Modifier.padding(it)) {
                      App()
                  }
              }


          }
        }
    }
    @Composable
    fun App()
    {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "category") {
            composable("category") {
                CategoryScreen(onClick = {
                   navigateToDetailScreen(navController,it)
                })
            }

            composable("details/{category}",
                arguments = listOf(
                    navArgument("category") {
                        type= NavType.StringType
                    }
                )
                ) {
                DetailScreen()
            }
        }
    }

    private fun navigateToDetailScreen(navController: NavController, category: String) {
        GlobalScope.launch(Dispatchers.Main) {

            delay(1000)
            navController.navigate("details/$category")
        }
    }

}

