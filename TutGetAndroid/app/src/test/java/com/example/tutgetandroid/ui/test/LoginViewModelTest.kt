package com.example.tutgetandroid.ui.test

import com.example.tutgetandroid.ui.LoginViewModel
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

class LoginViewModelTest {

    private val viewModel = LoginViewModel()

    @Test
    fun loginViewModel_login_userUpdated(){
        var currentLoginUiState = viewModel.uiState.value
        val correctUser = "Tom"

        viewModel.updateUsername(correctUser)
        viewModel.updatePassword("Password")

        println(viewModel.userInputUsername)
        println(viewModel.userInputPassword)
        viewModel.checkLogin()

        currentLoginUiState = viewModel.uiState.value
        assertTrue(currentLoginUiState.loggedIn)
        assertEquals(currentLoginUiState.username,correctUser)

    }


}