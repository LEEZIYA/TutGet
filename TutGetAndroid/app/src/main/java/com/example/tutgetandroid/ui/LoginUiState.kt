package com.example.tutgetandroid.ui

data class LoginUiState(
    val loggedIn: Boolean = false,
    val username: String = "",
    val loggedInWrong: Boolean = false

)