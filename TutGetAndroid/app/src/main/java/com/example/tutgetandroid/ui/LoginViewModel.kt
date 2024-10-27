package com.example.tutgetandroid.ui

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    var userInputUsername by mutableStateOf("")
        private set

    var userInputPassword by mutableStateOf("")
        private set


    fun updateUsername(username: String){
        userInputUsername = username
    }

    fun updatePassword(password: String){
        userInputUsername = password
    }

    init {
        resetLogin()
    }
    /*hard coded*/
    val userMap = mapOf (
        "Tom" to "Password",
        "Mike" to "Password2"
    )
    /*hard coded*/


    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private lateinit var currentUserName:String
    private fun login(username:String,password:String): Boolean{
            val storedPassword = userMap[username]
            if (password.equals(storedPassword)){
                currentUserName = username
                return true
            } else {
                return false
            }

            }

    fun checkLogin(){
        if(login(userInputUsername,userInputPassword)){
            _uiState.update { currentState ->
                currentState.copy(loggedIn = true,username = userInputUsername)
            }

        }
        else{
            _uiState.update { currentState ->
                currentState.copy(loggedIn = false,username = "")
            }
        }
    }
    //private var listings: MutableList<String> = mutableSetOf()
    private var listings: MutableSet<String> = mutableSetOf()

    fun resetLogin() {
//        usedWords.clear()
        _uiState.value = LoginUiState(loggedIn = false)
    }

    init {
        resetLogin()
    }
}