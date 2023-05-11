@file:OptIn(ExperimentalMaterial3Api::class)

package com.store.food.feature.favourite

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.store.food.data.local.entity.ProductEntity
import com.store.food.feature.find.FindViewModel
import com.store.food.navigation.BottomAppBarNavigation
import com.store.food.ui.theme.FoodTheme

//TODO: Implementar la pantalla de favoritos con el BottomAppBarNavigation
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Favourite(navController: NavController, favoriteViewModel: FavoriteViewModel) {


    Scaffold(bottomBar = {
        BottomAppBar(modifier = Modifier.fillMaxWidth(), content = {
            BottomAppBarNavigation(navController)
        })
    }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            FoodTheme {
                val productsState by favoriteViewModel.products.collectAsState(initial = emptyList())
                val key = remember { mutableStateOf(0) }
                LaunchedEffect(Unit) {
                    favoriteViewModel.fetchFavorites()
                }
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(128.dp),
                    modifier = Modifier
                        .fillMaxSize(),
                    state = LazyGridState(key.value)
                ) {
                    val gridItemSpan = productsState.chunked(2)
                    items(gridItemSpan.size) { index ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            gridItemSpan[index].forEach { product ->
                                ProductItemFavorite(product = product)
                            }
                        }
                    }
                }

                LaunchedEffect(productsState) {
                    key.value = key.value + 1
                }
            }

        }
    }
}

@Composable
fun ProductItemFavorite(product: ProductEntity) {
    Card(modifier = Modifier.fillMaxWidth()) {
        ProductImgFavorite(product = product)
        ProductNameFavorite(product = product)
    }
}

@Composable
fun ProductNameFavorite(product: ProductEntity) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(6.dp)) {
        Text(text = product.id, style = MaterialTheme.typography.bodySmall)
        Text(
            text = product.title,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            softWrap = true,
            maxLines = 2
        )
    }
}

@Composable
fun ProductImgFavorite(product: ProductEntity) {
    Row(modifier = Modifier.fillMaxWidth().background(Color.White)) {
        AsyncImage(
            model = product.image,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Fit
        )
    }
}



