package com.example.myfisrtapp.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myfisrtapp.R
import com.example.myfisrtapp.ui.theme.MyFisrtAppTheme
import kotlinx.coroutines.delay

@Composable
fun ActivitySplash (navController:NavController){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Splash()
        LaunchedEffect(key1 = true) {
            delay(2000)
            navController.navigate("Welcome")
        }
    }
                // A surface container using the 'background' color from the them
}

@Composable
fun Splash() {
    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Image(painter = painterResource(id = R.drawable.logo) , contentDescription ="logoSplash")
        Spacer(modifier = Modifier.height(30.dp))
        Image(painter = painterResource(id = R.drawable.logo_2) , contentDescription ="logo")
        LoadingAnimation()


    }
}
@Composable
fun LoadingAnimation(
    modifier: Modifier= Modifier,
    circleSize: Dp = 25.dp,
    circleColor: Color = MaterialTheme.colorScheme.primary,
    spaceBetweenL: Dp = 10.dp,
    travelDistance: Dp = 20.dp
){
    val circles = listOf(
        remember { Animatable(initialValue = 0f)},
        remember { Animatable(initialValue = 0f)},
        remember { Animatable(initialValue = 0f)}
    )
    circles.forEachIndexed{index, animatable ->
        LaunchedEffect(key1 = animatable) {
            delay(index * 100L)
            animatable.animateTo(

                targetValue = 1f ,
                animationSpec =  infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1200
                        0.0f at 0 with LinearOutSlowInEasing
                        1f at 300 with LinearOutSlowInEasing
                        0.0f at 600 with LinearOutSlowInEasing
                        0.0f at 1200 with LinearOutSlowInEasing
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }

        }

    val distance = with(LocalDensity.current){travelDistance.toPx()}
    val circlesValues = circles.map { it.value }
    Row {
        circlesValues.forEachIndexed { index, fl ->
            Box(modifier = Modifier
                .size(circleSize)
                .graphicsLayer { translationY = -fl * distance }
                .background(color = circleColor, shape = CircleShape)
            )
            if (index != circles.size-1)
            {
                Spacer(modifier = Modifier.width(spaceBetweenL))
            }
        }
    }

}
@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    MyFisrtAppTheme {
        Splash()
    }
}