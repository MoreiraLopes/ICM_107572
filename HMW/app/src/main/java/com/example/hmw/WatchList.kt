package com.example.hmw

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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