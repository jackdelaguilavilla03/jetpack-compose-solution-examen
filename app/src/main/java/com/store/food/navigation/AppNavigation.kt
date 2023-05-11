package com.store.food.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.store.food.feature.favourite.FavoriteViewModel
import com.store.food.feature.favourite.Favourite
import com.store.food.feature.find.FindViewModel
import com.store.food.feature.find.Find
import com.store.food.feature.home.Home

@Composable
fun AppNavigation(findViewModel: FindViewModel, favoriteViewModel: FavoriteViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home" ){
        composable( "Home" ){
            Home(navController)
        }
        composable( "Products" ){
            Find(navController, findViewModel)
        }
        composable("Favourites" ){
            Favourite(navController, favoriteViewModel)
        }
    }
}