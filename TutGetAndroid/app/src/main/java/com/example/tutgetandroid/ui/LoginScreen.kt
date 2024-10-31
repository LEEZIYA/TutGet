package com.example.tutgetandroid.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tutgetandroid.EditTextField
import com.example.tutgetandroid.R
import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedTextField

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextField
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.tutgetandroid.MyListingScreen
import com.example.tutgetandroid.ui.theme.TutGetAndroidTheme

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel()
) {
    val loginUiState by loginViewModel.uiState.collectAsState()
    Surface(
//                   modifier = Modifier.fillMaxWidth(),
//                   //color = MaterialTheme.colorScheme.primaryContainer
//        color = Color.Cyan
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primaryContainer,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            //color = MaterialTheme.colorScheme.secondary
        ) {

            if(loginUiState.loggedIn){
                Text(
                    text = "Hi Welcome to Tutget, You have logged in as ${loginUiState.username}",
                    modifier = Modifier.padding(24.dp)
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { loginViewModel.resetLogin() }
                ) {
                    Text(stringResource(R.string.logout))
                }
            }
            else {
                Text(
                    text = "Hi Welcome to Tutget, Please login to proceed!",
                    modifier = Modifier.padding(24.dp)
                )
                LoginLayout(
                    userName = loginViewModel.userInputUsername,
                    passWord = loginViewModel.userInputPassword,
                    loggedInWrong = loginUiState.loggedInWrong,
                    onUsernameChanged = { loginViewModel.updateUsername(it) },
                    onPasswordChanged = { loginViewModel.updatePassword(it) },
                    //onKeyboardDone = { },
                    //loggedin = loginUiState.loggedin,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                    //.padding(mediumPadding)
                )


                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { loginViewModel.checkLogin() }
                ) {
                    Text(stringResource(R.string.login))
                }
            }
        }
    }
//

}

@Composable
fun LoginLayout(
    userName:String,
    passWord:String,
    loggedInWrong:Boolean,
    onUsernameChanged:(String)->Unit,
    onPasswordChanged:(String)->Unit,
    //onKeyboardDone:()->Unit,
    //loggedin:Boolean,
    modifier: Modifier = Modifier
) {
//    var userName by remember { mutableStateOf("") }
//    var passWord by remember { mutableStateOf("") }
//    Surface(
////                   modifier = Modifier.fillMaxWidth(),
////                   //color = MaterialTheme.colorScheme.primaryContainer
////        color = Color.Cyan
//        shape = MaterialTheme.shapes.medium,
//        color = MaterialTheme.colorScheme.primaryContainer,
//    ) {
//        Column(
//            modifier = modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally,
//            //color = MaterialTheme.colorScheme.secondary
//        )  {
//            Text(
//                text = "Hi Welcome to Tutget, Please login to proceed!",
//                modifier = modifier.padding(24.dp)
//            )
//            EditTextField(fieldName = "User Name",
//                keybordType = KeyboardType.Email,
//                value = "",
//                onValueChange = onUsernameChanged,
//                //on
//                modifier = Modifier
//                    .padding(bottom = 32.dp)
//
//            )
//            OutlinedTextField(
//                value = userName,
//                singleLine = true,
//                modifier = Modifier.fillMaxWidth(),
//                onValueChange = onUsernameChanged,
//                label = Text(stringResource(R.string.userName)) ,
//                isError = false,
//                keyboardOptions = KeyboardOptions.Default.copy(
//                    imeAction = ImeAction.Done
//                ),
//                keyboardActions = KeyboardActions(
//                    onDone = { onKeyboardDone() }
//                ),
//            )
    TextField(
        value = userName,
        onValueChange = onUsernameChanged,
        isError = loggedInWrong,
        modifier = modifier,
        label = {if (loggedInWrong) {
                    Text(stringResource(R.string.wrong_login))
                } else {
                    Text(stringResource(R.string.userName))
                }
                },
        singleLine = false,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
    TextField(
        value = passWord,
        onValueChange = onPasswordChanged,
        isError = loggedInWrong,
        modifier = modifier,
        label = { if (loggedInWrong) {
            Text(stringResource(R.string.wrong_login))
        }
            Text(stringResource(R.string.passWord))

                },
        visualTransformation = PasswordVisualTransformation(),
        singleLine = false,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )

//            Spacer(modifier = Modifier.height(20.dp))
//            EditTextField(fieldName = "Password",
//                keybordType = KeyboardType.Password,
//                value = "",
//                onValueChange = onUsernameChanged,
//                modifier = Modifier
//                    .padding(bottom = 32.dp)
//
//            )
//            Spacer(modifier = Modifier.height(20.dp))

//            Button(onClick = onSubmit) {
//                Text(stringResource(R.string.login), fontSize = 24.sp)
//            }
        }

//@Composable
//private fun LogOutDialog(
//    onPlayAgain: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    val activity = (LocalContext.current as Activity)
//
//    AlertDialog(
//        onDismissRequest = {
//            // Dismiss the dialog when the user clicks outside the dialog or on the back
//            // button. If you want to disable that functionality, simply use an empty
//            // onDismissRequest.
//        },
//        title = { Text(stringResource(R.string.logout)) },
//        text = { Text(stringResource(R.string.logoutText, 0)) },
//        modifier = modifier,
//        dismissButton = {
//            TextButton(
//                onClick = {
//                    activity.finish()
//                }
//            ) {
//                Text(text = stringResource(R.string.exit))
//            }
//        },
//        confirmButton = {
//            TextButton(
//                onClick = {
//                    onPlayAgain()
//                }
//            ) {
//                Text(text = stringResource(R.string.play_again))
//            }
//        }
//    )
//}
//@Preview
//@Composable
//fun TutGetMyListingPagePreview() {
//    TutGetAndroidTheme {
//        LoginScreen()
//    }
//}