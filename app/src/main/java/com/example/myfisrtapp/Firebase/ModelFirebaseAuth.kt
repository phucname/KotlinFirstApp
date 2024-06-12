package com.example.myfisrtapp.Firebase

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

data class LoginState(
    val isLogin : Boolean = false
)
class ModelFirebaseAuth(): ViewModel() {
   var  stateLogin by mutableStateOf(LoginState())
    var firebaseAuth = FirebaseAuth.getInstance()
   suspend fun UserLogin(email: String, password: String): Boolean {

       return suspendCancellableCoroutine { continuation ->
           firebaseAuth.signInWithEmailAndPassword(email, password)
               .addOnCompleteListener { task ->
                   if (task.isSuccessful) {
                       continuation.resume(true)
                   } else {
                       task.exception?.let { exception ->
                           continuation.resumeWithException(exception)
                       } ?: continuation.resume(false)
                   }
               }
       }
    }
    fun UserLogOut(){
        
    }
    init {

    }
}