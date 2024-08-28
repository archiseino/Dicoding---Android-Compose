package com.example.jetcoffe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetcoffe.model.Menu
import com.example.jetcoffe.model.dummyBestSellerMenu
import com.example.jetcoffe.model.dummyCategory
import com.example.jetcoffe.model.dummyMenu
import com.example.jetcoffe.ui.components.BottomBar
import com.example.jetcoffe.ui.components.CategoryItem
import com.example.jetcoffe.ui.components.HomeSection
import com.example.jetcoffe.ui.components.MenuItem
import com.example.jetcoffe.ui.components.Search
import com.example.jetcoffe.ui.theme.JetCoffeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetCoffeeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JetCoffeeApp()
                }
            }
        }
    }
}

@Composable
fun JetCoffeeApp(modifier: Modifier = Modifier) {
    Scaffold(
        bottomBar = { BottomBar() }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            Banner()
            HomeSection(
                title = stringResource(R.string.section_category),
                content = { CategoryRow() }
            )
            HomeSection(
                title = stringResource(R.string.section_best_seller_menu),
                content = { MenuRow(dummyMenu) }
            )
            HomeSection(title = stringResource(R.string.section_best_seller_menu),
                content = { MenuRow(dummyBestSellerMenu) }
            )
        }

    }
}

@Composable
fun MenuRow(listMenu: List<Menu>, modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
    ) {
        items(listMenu, key = { it.title }) {
            MenuItem(menu = it)
        }
    }
}

@Composable
fun CategoryRow(modifier: Modifier = Modifier) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(dummyCategory, key = { it.textCategory }) {
            CategoryItem(category = it)
        }
    }
}

@Composable
fun Banner(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.banner),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.height(160.dp)
        )
        Search()
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetCoffeeTheme {
        JetCoffeeApp()
    }
}