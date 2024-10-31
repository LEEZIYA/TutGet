package com.example.tutgetandroid.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tutgetandroid.Listing

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
fun ListingScreen(
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
