package com.example.myfisrtapp.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformSpanStyle
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.myfisrtapp.Common.Contans
import com.example.myfisrtapp.Common.TextFieldStyleManager
import com.example.myfisrtapp.Common.TextFieldStyles

import com.example.myfisrtapp.Firebase.ModelFirebaseAuth

import com.example.myfisrtapp.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@Composable
fun LoginActivity (navController: NavController) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginView(navController)
                }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(navController: NavController) {
    val modelFirebaseAuth = ModelFirebaseAuth()
    // A surface container using the 'backgro
    var emailState by rememberSaveable { mutableStateOf("") }
    var passWordState by rememberSaveable { mutableStateOf("") }


    Column (modifier = Modifier
        .width(270.dp)
        .padding(start = 15.dp, end = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Image(painter = painterResource(id = R.drawable.logo_2)
            , contentDescription ="Logo_login"
            , modifier = Modifier.padding(top = 84.dp))

        ViewInput(value = emailState,  onValueChange = {  emailState = it ; println(it) } , Contans.PlanceEmail, textFieldStyleManager = TextFieldStyleManager())

        ViewInput(value = passWordState, onValueChange = { passWordState = it }, Contans.PlancePassWord , textFieldStyleManager = TextFieldStyleManager())

        Button(onClick = { Model(emailState, passWordState, modelFirebaseAuth, navController)}
            , modifier = Modifier.widthIn(270.dp)
        , border = BorderStroke(2.dp, Color.Transparent)
        , shape = RoundedCornerShape(8.dp)
        , colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF0000))) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(20.dp))
        ViewRegister()

    }
}


  @OptIn(DelicateCoroutinesApi::class)
  fun Model(email: String, passWord: String, modelFirebaseAuth: ModelFirebaseAuth, navController: NavController ) {
    GlobalScope.launch(Dispatchers.Main) {
        try {
            var sucuss = modelFirebaseAuth.UserLogin(email, password = passWord)
            println("succ:$sucuss")
        }catch (ex: Exception){
            println("erro: $ex")
        }

    }

}


@Composable
fun ViewInput (value: String, onValueChange: (String) -> Unit,placeholder: String,textFieldStyleManager: TextFieldStyleManager){
    TextField(
        value = value,
       placeholder = { Text(text = placeholder)},
        onValueChange= onValueChange,
        textStyle = TextStyle(textAlign = TextAlign.Center),
        colors =textFieldStyleManager.textFieldColors() ,
        modifier = Modifier
            .padding(top = 50.dp, bottom = 20.dp)
            .border(2.dp, Color.Red, shape = RoundedCornerShape(10))
    )
}

@Composable
fun ViewRegister(){
            Text(text = "New here", textAlign = TextAlign.Center
                , fontSize = 18.sp, fontStyle = FontStyle.Italic
                , color = Color(0x80000000))
            Button(onClick = { }
                , modifier = Modifier
                , border = BorderStroke(2.dp, Color(0x8000A3FF.toInt()))
                , shape = RoundedCornerShape(2.dp)
                , colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Text(text = "Lgin", color = Color.DarkGray)
            }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4(){
    LoginActivity(navController = rememberNavController())
}