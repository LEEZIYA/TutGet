package com.example.tutgetandroid

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.tutgetandroid.ui.theme.TutGetAndroidTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ElevatedButton
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.Button
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Add
import android.util.Log
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.material3.AlertDialog
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.TextButton

import com.example.tutgetandroid.ui.LoginScreen
import com.example.tutgetandroid.ui.TutgetScreen

private const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")
        enableEdgeToEdge()

        setContent {
            TutGetAndroidTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }


//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
                //DiceRollerApp()
                TutgetScreen()
            }


        }
    }

}
    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Surface(color = Color.Cyan) {
            Text(
                text = "Hi Welcome to Tutget, $name!",
                modifier = modifier.padding(24.dp)
            )
        }
    }

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    TutGetAndroidTheme {
//        Greeting("Android")
//    }
//}

@Composable
fun LoginPage2(
    onSubmit:()->Unit,
    modifier: Modifier = Modifier) {
    var userName by remember { mutableStateOf("") }
    var passWord by remember { mutableStateOf("") }
    Surface(
//                   modifier = Modifier.fillMaxWidth(),
//                   //color = MaterialTheme.colorScheme.primaryContainer
//        color = Color.Cyan
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primaryContainer,
            ) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        //color = MaterialTheme.colorScheme.secondary
    )  {
        Text(
            text = "Hi Welcome to Tutget, Please login to proceed!",
            modifier = modifier.padding(24.dp)
        )
        EditTextField(fieldName = "User Name",
            keybordType = KeyboardType.Email,
            value = "",
            onValueChange = {userName = it},
            modifier = Modifier
                .padding(bottom = 32.dp)

        )
        Spacer(modifier = Modifier.height(20.dp))
        EditTextField(fieldName = "Password",
            keybordType = KeyboardType.Password,
            value = "",
            onValueChange = {passWord = it},
            modifier = Modifier
                .padding(bottom = 32.dp)

        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onSubmit) {
            Text(stringResource(R.string.login), fontSize = 24.sp)
        }
    }
        }
}




    @OptIn(ExperimentalMaterial3Api::class)
    //@Preview(showBackground = true, widthDp = 320)
    //@Preview(showBackground = true)
    @Composable
    fun TutGetApp2(modifier: Modifier = Modifier) {
        var loggedIn by rememberSaveable {mutableStateOf(false)}
        var submitted by rememberSaveable {mutableStateOf(false)}
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
            bottomBar = { TutGetBottomNavigation() },


        ) { innerPadding ->
            LoginScreen()
//            Column(
//                modifier = modifier,
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Top
//            ){

//            if(!loggedIn){
//                LoginPage2(onSubmit = {loggedIn = true},modifier = Modifier.padding(innerPadding))
//            }
//            else {



//                if (submitted) {
//                    Text(
//                        text = stringResource(R.string.listingSubmitted),
//                        style = MaterialTheme.typography.displaySmall,
//                        modifier = Modifier
//                            .padding(innerPadding),
//                        fontSize = 24.sp
//                    )
//                    Spacer(modifier = Modifier.height(20.dp))
//
//                }

//                PostListing(
//                    submitted = submitted,
//                    modifier = Modifier
//                        .fillMaxSize()
//
//                        .padding(innerPadding),
//                    onPostClicked = { submitted = !submitted }
//                    //onNewClicked = {submitted = false}
//                )
//            }

//            }


        }
    }

@Composable
    fun PostListing(
    submitted:Boolean,
    modifier: Modifier = Modifier,
    fieldNames: List<String> = listOf(stringResource(R.string.listingAcadLvl),stringResource(R.string.listingSubject),stringResource(R.string.listingDescription),stringResource(R.string.listingAddress),stringResource(R.string.listingTiming)),
    onPostClicked: () -> Unit,
//    onNewClicked: () -> Unit,
//    clicked: Boolean = false
    ) {
    var content by remember { mutableStateOf("") }
    if (submitted) {
        Text(
            text = stringResource(R.string.listingSubmitted),
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(20.dp))

    }


    Column(
            modifier = modifier
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

                Text(
                    text = stringResource(R.string.listingPageTitle),
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                        .padding(bottom = 16.dp, top = 40.dp)
                        .align(alignment = Alignment.Start)
                )
            for(fieldName in fieldNames){
                EditTextField(fieldName = fieldName,
                    keybordType = KeyboardType.Text,
                    value = content,
                    onValueChange = {content = it},
                    modifier = Modifier
                        .padding(bottom = 32.dp)
                        .fillMaxWidth()
                    )
                Spacer(modifier = Modifier.height(20.dp))
            }
//            Text(
//                text = stringResource(R.string.listingDescription),
//                modifier = Modifier
//                    .padding(bottom = 16.dp, top = 40.dp)
//                    .align(alignment = Alignment.Start)
//            )
//            EditTextField(modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth())
//            Button(onClick = (if (clicked) onNewClicked else onPostClicked)) {
//                Text(if (clicked) stringResource(R.string.New) else stringResource(R.string.post), fontSize = 24.sp)
//            }
                            Button(onClick = {

                                onPostClicked()
                            }) {
                                //var submitted by remember {mutableStateOf(false)}
                    Text(stringResource(R.string.post), fontSize = 24.sp)
            }


        }
    }




    @Composable
    fun EditTextField(fieldName: String,
                      keybordType: KeyboardType,
                      value: String, onValueChange: (String) -> Unit,
                      modifier: Modifier = Modifier) {
        //var content by remember { mutableStateOf("") }
        TextField(
            value = value,
            onValueChange = onValueChange,
//            {
//                content = it
//            },
            modifier = modifier,
            label = { Text(fieldName)},
            singleLine = false,
            keyboardOptions = KeyboardOptions(keyboardType = keybordType)
                    //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }

    //@Preview
    @Composable
    fun DiceRollerApp() {
        DiceWithButtonAndImage(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
        )
    }

    @Composable
    fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {

        var result by remember { mutableStateOf(1) }
        //var result = 1
        val imageResource = when (result) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = result.toString()
            )
            Text(result.toString(), fontSize = 24.sp)
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { result = (1..6).random() }) {
                Text(stringResource(R.string.post), fontSize = 24.sp)
            }

        }
    }

    @Composable
    fun Listing(name: String,
                detail: String,
                modifier: Modifier = Modifier) {
        var expanded by remember { mutableStateOf(false) }
        //val extraPadding = if (expanded) 48.dp else 0.dp
        Surface(
            color = MaterialTheme.colorScheme.primary,
            modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Row(modifier = Modifier.padding(24.dp)) {
                Column(
                    modifier = Modifier.weight(1f)
                        //.padding(bottom = extraPadding)

                ) {
                    //Text(text = "Hello ")
                    Text(text = name)
                    if (expanded) {
                        Spacer(Modifier.height(16.dp))
                        Text(text = detail)
                        Spacer(Modifier.height(16.dp))
                    }
                }
                ElevatedButton(
                    onClick = { expanded = !expanded }
                ) {
                    Text(if (expanded) "Show less" else "Show details")
                }
            }
        }
    }

@Composable
fun MyListingScreen(
    modifier: Modifier = Modifier,
    names: List<String> = listOf("Listing1", "Listing2"),
    listingDetails: List<String> = listOf("JC2, Economics, Friday", "P1, Chinese, Wednesday")

) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 4.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        //SearchBar(Modifier.padding(horizontal = 16.dp))
//        HomeSection(title = R.string.align_your_body) {
//            AlignYourBodyRow()
//        }
//        HomeSection(title = R.string.favorite_collections) {
//            FavoriteCollectionsGrid()
//        }
        var n = 0
        for (name in names) {
            Listing(name = name, detail=listingDetails.get(n))
            n++
        }
        Spacer(Modifier.height(16.dp))
    }
}

    @Composable
    private fun TutGetBottomNavigation(modifier: Modifier = Modifier) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            modifier = modifier
        ) {
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(R.string.bottom_navigation_add)
                    )
                },
                selected = true,
                onClick = {}
            )
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(R.string.bottom_navigation_home)
                    )
                },
                selected = true,
                onClick = {}
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
                onClick = {}
            )
        }
    }

@Composable
private fun LoginFailDialog(
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onDismissRequest.
        },
        title = { Text(text = stringResource(R.string.loginFailed)) },
       // text = { Text(text = stringResource(R.string.you_scored, score)) },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = stringResource(R.string.exit))
            }
        },
        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text(text = stringResource(R.string.login_again))
            }
        }
    )
}


@Preview
@Composable
fun TutGetAppPreview() {
    TutGetAndroidTheme {
        TutGetApp2(Modifier.fillMaxSize())
    }

}

//@Preview
//@Composable
//fun navigationPreview() {
//    TutGetAndroidTheme {
//        TutGetBottomNavigation(Modifier.fillMaxSize())
//    }
//
//}
@Preview
@Composable
fun TutGetPostListingPagePreview() {
    PostListing(
        submitted=true,
        modifier = Modifier
            .fillMaxSize(),


        onPostClicked = { }
        //onNewClicked = {submitted = false}
    )

}
@Preview
@Composable
fun TutGetMyListingPagePreview() {
    TutGetAndroidTheme {
        MyListingScreen()
    }
}



