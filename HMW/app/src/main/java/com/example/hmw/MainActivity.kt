package com.example.hmw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hmw.ui.theme.HmwTheme
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WatchListScreen()
        }
    }
}

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



@Composable
fun WatchList(watchList: List<String>, onWatched: (String) -> Unit) {
    var selectedItems by remember { mutableStateOf(emptyList<String>()) }

    Column {
        watchList.forEach { item ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = selectedItems.contains(item),
                    onCheckedChange = { isChecked ->
                        if (isChecked) {
                            selectedItems = selectedItems + item
                        } else {
                            selectedItems = selectedItems - item
                        }
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = item)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                selectedItems.forEach { item ->
                    onWatched(item)
                }
                selectedItems = emptyList() // Reset selected items
            },
            enabled = selectedItems.isNotEmpty()
        ) {
            Text(text = "Marcar como Assistidos")
        }
    }
}




@Composable
fun AddItemSection(newItem: String, onNewItemChange: (String) -> Unit, onAddItem: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = newItem,
            onValueChange = onNewItemChange,
            label = { Text("Adicionar filme") },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = onAddItem) {
            Text(text = "Adicionar")
        }
    }
}

