package com.example.tutgetandroid.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.tutgetandroid.R
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

enum class TutGetScreens() {
    Profile,
    Login,
    QnA,
    Listing
}

@Composable
private fun TutGetBottomNavigation(
    loggedIn:Boolean = false,
    onProfileClicked:(Boolean)->Unit,
    onQnAClicked:(Boolean)->Unit,
    onListingClicked:(Boolean)->Unit,
    modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_QnA)
                )
            },
            selected = true,
            onClick = {onQnAClicked(loggedIn)}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_listing)
                )
            },
            selected = true,
            onClick = {onListingClicked(loggedIn)}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_profile)
                )
            },
            selected = false,
            onClick = {onProfileClicked(loggedIn)}
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true, widthDp = 320)
//@Preview(showBackground = true)
@Composable
fun TutgetScreen(
    viewModel: LoginViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier) {
    var loggedIn by rememberSaveable { mutableStateOf(false) }
    var submitted by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "TutGet",
                        fontWeight = FontWeight.Bold
                    )
                }
                ,
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )
        },
        bottomBar = { TutGetBottomNavigation(loggedIn,
            onProfileClicked={navController.navigate(TutGetScreens.Profile.name)},
            onQnAClicked={navController.navigate(TutGetScreens.QnA.name)},
            onListingClicked={navController.navigate(TutGetScreens.Listing.name)},
        ) },


        ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        val loggedIn = uiState.loggedIn

        NavHost(
            navController = navController,
            startDestination = TutGetScreens.Profile.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = TutGetScreens.Profile.name) {
                if(!loggedIn){
                LoginScreen(loginViewModel=viewModel)}
                else{
                    Text("You have logged in, welcome")
                }
            }
            composable(route = TutGetScreens.Listing.name) {

                if(loggedIn) {
                    ListingScreen()
                }
                else{
                    Text("Please logged in to access Listing")
                }
            }
            composable(route = TutGetScreens.QnA.name) {
                //QnAScreen()
                if(loggedIn){
                Text("QnA Screen under construction")}
                else{
                    Text("Please logged in to access QnA")
                }

            }
        }
        //LoginScreen()


    }
}
