package com.example.hmw

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WatchListScreen(viewModel: WatchListViewModel = viewModel()) {
    var newItem by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Lista de filmes por assistir:")
        Spacer(modifier = Modifier.height(8.dp))
        WatchList(watchList = viewModel.watchList) { selectedItems ->
            viewModel.markAsWatched(selectedItems)
        }

        Spacer(modifier = Modifier.height(16.dp))
        AddItemSection(
            newItem = newItem,
            onNewItemChange = { newItem = it },
            onAddItem = {
                viewModel.addItemToWatchList(newItem)
                newItem = ""
            }
        )
    }
}