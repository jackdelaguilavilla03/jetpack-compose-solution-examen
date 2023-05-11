@file:OptIn(ExperimentalMaterial3Api::class)

package com.store.food.feature.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.store.food.R
import com.store.food.navigation.BottomAppBarNavigation
import com.store.food.ui.theme.FoodTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(navController: NavController) {
    Scaffold(bottomBar = {
        BottomAppBar(modifier = Modifier.fillMaxWidth(), content = {
            BottomAppBarNavigation(navController)
        })
    }) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = Color(0xfff2f2f2)
        ) {
            FoodTheme {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(id = R.drawable.food),
                        contentDescription = "Food Image"
                    )
                    Text(
                        text = "Welcome to Home",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color(0xff000000),
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomePreview() {
}