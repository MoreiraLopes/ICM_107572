package com.example.hmw

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WatchListViewModel : ViewModel() {
    // Lista de filmes e séries para assistir
    var watchList by mutableStateOf(
        listOf(
            "The Matrix",
            "Breaking Bad",
            "Inception"
        )
    )
        private set

    // Função para adicionar um novo item à lista
    fun addItemToWatchList(item: String) {
        watchList = watchList + item
    }

    // Função para marcar um filme como assistido
    fun markAsWatched(item: String) {
        watchList = watchList.filter { it != item }
    }
}