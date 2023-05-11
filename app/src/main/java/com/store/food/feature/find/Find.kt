@file:OptIn(ExperimentalMaterial3Api::class)

package com.store.food.feature.find

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.store.food.data.remote.model.Product
import com.store.food.navigation.BottomAppBarNavigation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Find(navController: NavController, viewModel: FindViewModel) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                content = {
                    BottomAppBarNavigation(navController)
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                ProductSearch(viewModel = viewModel)
                ProductList(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductSearch(viewModel: FindViewModel) {
    val name by viewModel.name.observeAsState("")
    val focusManager = LocalFocusManager.current
    Row {
        OutlinedTextField(
            value = name,
            onValueChange = { viewModel.update(it) },
            leadingIcon = {
                Icon(Icons.Filled.Search, contentDescription = null)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    viewModel.fetchByName()
                    focusManager.clearFocus()
                }),
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .size(48.dp)
        )
    }
}

@Composable
fun ProductList(viewModel: FindViewModel) {
    val products by viewModel.products.observeAsState(listOf())
    LazyColumn{
        items(products) { product ->
            ProductCard(
                product,
                insertProd = { viewModel.insert(product) },
                deleteProd = { viewModel.delete(product) }
            )
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    insertProd: () -> Unit,
    deleteProd: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp,
        )
    ) {
        Row {
            ProductImg(product)
            ProductItem(product, insertProd, deleteProd)
        }
    }
}

@Composable
fun ProductItem(
    product: Product,
    insertProd: () -> Unit,
    deleteProd: () -> Unit
) {
    val isFav = remember { mutableStateOf(false) }
    isFav.value = product.favorite
    Spacer(modifier = Modifier.width(8.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(product.id)
            Text(
                product.title,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                modifier = Modifier.width(200.dp),
                softWrap = true
            )
        }
        IconButton(
            onClick = {
                if (isFav.value) {
                    deleteProd()
                } else {
                    insertProd()
                }
                isFav.value = !isFav.value
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                Icons.Filled.Favorite, contentDescription = null,
                tint = if (isFav.value) {
                    MaterialTheme.colorScheme.primary
                } else {
                    Color.LightGray
                }
            )
        }
    }
}

@Composable
fun ProductImg(
    product: Product
) {
    AsyncImage(
        model = product.image,
        contentDescription = product.imageType,
        modifier = Modifier
            .size(100.dp)
            .clip(shape = MaterialTheme.shapes.medium),
        contentScale = ContentScale.Crop
    )
}