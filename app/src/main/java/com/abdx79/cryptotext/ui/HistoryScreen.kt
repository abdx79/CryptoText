package com.abdx79.cryptotext.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun MakeHistoryScreen(modifier: Modifier = Modifier) {
    HistoryElement()
}


@Composable fun HistoryElement(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var textValue: String by rememberSaveable {
        mutableStateOf("")
    }

    var makeToast: Boolean by remember { mutableStateOf(false) }

    if (makeToast) {
        Toast.makeText(LocalContext.current, textValue, Toast.LENGTH_SHORT).show()
        makeToast = false
    }
    Row {
        TextField(value = textValue, onValueChange = { textValue = it })
        Button(onClick = {makeToast = true}){
            Text(text = "Make a toast")
        }
    }
}
