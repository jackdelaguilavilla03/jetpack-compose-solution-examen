package com.store.food.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun BottomAppBarNavigation(navController: NavController) {

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        IconBottom(icon = Icons.Default.Home, text = "Home", navController)
        IconBottom(icon = Icons.Default.Search, text = "Products", navController)
        IconBottom(icon = Icons.Default.Favorite, text = "Favourites", navController)
    }
}

@Composable
fun IconBottom(icon: ImageVector, text: String, navController: NavController) {
    Button(
        onClick = { navController.navigate(text) },
        colors = ButtonDefaults.buttonColors(
            contentColor = androidx.compose.ui.graphics.Color.Black,
            containerColor = androidx.compose.ui.graphics.Color.Transparent
        ),
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
                ) {
            Icon(icon, contentDescription = null)
            Text(text = text)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BottomAppBarNavigationPreview() {
}