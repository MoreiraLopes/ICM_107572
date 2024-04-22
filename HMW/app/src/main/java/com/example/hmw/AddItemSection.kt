package com.example.hmw

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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