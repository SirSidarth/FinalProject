package com.example.finalproject

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private val _tiles = MutableStateFlow(
        listOf(
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
    )
    val tiles: StateFlow<List<Tile>> = _tiles

    private val _songs = _tiles.value.associate { tile ->
        tile.label to listOf(
            "${tile.label} Song 1",
            "${tile.label} Song 2",
            "${tile.label} Song 3",
            "${tile.label} Song 4",
            "${tile.label} Song 5"
        )
    }

    fun getSongsForPlaylist(playlist: String): List<String> {
        return _songs[playlist] ?: emptyList()
    }
}

data class Tile(val label: String)