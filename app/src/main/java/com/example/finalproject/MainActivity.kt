package com.example.finalproject

import androidx.compose.ui.graphics.Brush
import kotlin.random.Random

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf("Home") }

    Scaffold(
        topBar = { TopBar() },
        bottomBar = {
            BottomNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { inner ->
        Box(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
        ) {
            when (selectedTab) {
                "Home" -> SearchScreen()
                "Library" -> LibraryScreen()
            }
        }
    }
}

@Composable
fun SearchScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Gaana Music") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
        )

        val tiles = demoTiles()
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(tiles) { t -> CategoryCard(t) }
        }
    }
}

@Composable
fun LibraryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Your Library is Empty",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Start adding your favorite songs!",
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}

@Composable
fun BottomNavigationBar(selectedTab: String, onTabSelected: (String) -> Unit) {
    NavigationBar(containerColor = Color(0xFF1F1F1F)) {
        NavigationBarItem(
            selected = selectedTab == "Home",
            onClick = { onTabSelected("Home") },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.Gray,
                selectedTextColor = Color.White,
                unselectedTextColor = Color.Gray
            )
        )
        NavigationBarItem(
            selected = selectedTab == "Library",
            onClick = { onTabSelected("Library") },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Library") },
            label = { Text("Library") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.Gray,
                selectedTextColor = Color.White,
                unselectedTextColor = Color.Gray
            )
        )
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text("Search", fontSize = 40.sp, fontWeight = FontWeight.Bold)
            Text("Sidarth Sirimalla, Ujjwal Ujjwal", fontSize = 14.sp, color = Color.Gray)
        }
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(Color(0xFF5B4B8A)),
            contentAlignment = Alignment.Center
        ) { Text("SS", color = Color.White, fontWeight = FontWeight.Bold) }
    }
}

@Composable
fun CategoryCard(t: Tile) {
    // Generate two random colors for the gradient
    val color1 = remember { Color(Random.nextInt(100, 256), Random.nextInt(100, 256), Random.nextInt(100, 256)) }
    val color2 = remember { Color(Random.nextInt(100, 256), Random.nextInt(100, 256), Random.nextInt(100, 256)) }
    Button(
        onClick = { /* no-op */ },
        shape = RoundedCornerShape(24.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.8f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(24.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(color1, color2)
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.25f))
            )
            Text(
                t.label,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

data class Tile(val label: String)

fun demoTiles(): List<Tile> {
    return listOf(
        Tile("Pedda Puli Mix"),
        Tile("Ganna Music Live"),
        Tile("Ganna Music Radio"),
        Tile("Coming Soon"),
        Tile("Chatal Band"),
        Tile("Jatara Mix"),
        Tile("Country"),
        Tile("Pop"),
        Tile("Hindi Retro"),
        Tile("Punjabi Hits"),
        Tile("Telugu Hits"),
        Tile("Mass"),
        Tile("Top 50 India"),
        Tile("Pop Remix"),
        Tile("Bollywood Beats"),
        Tile("Chill Vibes"),
        Tile("Workout Anthems"),
        Tile("Classical Essentials"),
        Tile("Romantic Hits"),
        Tile("Party Time"),
        Tile("Focus Flow"),
        Tile("Indie India"),
        Tile("Global EDM"),
        Tile("Morning Motivation"),
        Tile("Evening Relax"),
        Tile("Rock Classics"),
        Tile("Jazz Cafe"),
        Tile("Instrumental Bliss"),
        Tile("Throwback 90s"),
        Tile("Kids Zone"),
        Tile("Travel Tunes")
    )
}