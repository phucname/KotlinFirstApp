package com.example.myfisrtapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myfisrtapp.screens.ActivitySplash
import com.example.myfisrtapp.screens.Home
import com.example.myfisrtapp.screens.LoginActivity
import com.example.myfisrtapp.screens.LoginView
import com.example.myfisrtapp.screens.WelcomeActivity
import com.example.myfisrtapp.ui.theme.MyFisrtAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "Login") {
                composable(route = "ActivitySplash"){ActivitySplash(navController)}
                composable(route = "Welcome"){ WelcomeActivity(navController) }
                composable(route = "Login"){ LoginActivity(navController) }
                composable(route = "Home"){ Home()}

                // Add more destinations similarly.
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyFisrtAppTheme {
        Greeting("Android")
    }
}