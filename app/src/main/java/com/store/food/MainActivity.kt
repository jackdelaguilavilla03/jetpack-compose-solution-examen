@file:OptIn(ExperimentalMaterial3Api::class)

package com.store.food

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.store.food.feature.favourite.FavoriteViewModel
import com.store.food.feature.find.FindViewModel
import com.store.food.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = applicationContext
            val findViewModel: FindViewModel by viewModels()
            val favoriteViewModel: FavoriteViewModel by viewModels()
            AppNavigation(findViewModel, favoriteViewModel)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {

}