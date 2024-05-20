package com.abdx79.cryptotext

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.abdx79.cryptotext.models.CipherDataHolder
import com.abdx79.cryptotext.models.ReadyDataModel

@Composable
fun AskPasswordDialog(data: String, onDismissRequest: () -> Unit, onOutputReady: (ReadyDataModel) -> Unit) {

    var password: String by remember { mutableStateOf("") }

    var startEncryption by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = { startEncryption = false; onDismissRequest() }, properties = DialogProperties(
            dismissOnBackPress = true, dismissOnClickOutside = false, usePlatformDefaultWidth = false
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp)
        ) {

            if (startEncryption.not()) {

                Column(Modifier.padding(16.dp)) {

                    val childModifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)

                    Text(text = "Enter Key", style = MaterialTheme.typography.headlineMedium, modifier = childModifier)

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = childModifier,
                        label = { Text(text = "key") },
                        maxLines = 1
                    )

                    Button(onClick = {
                        startEncryption = true
                    }, modifier = childModifier.padding(top = 8.dp)) {
                        Text(text = "Encrypt")
                    }
                }
            } else {
                PerformEncryption(dataHolder = CipherDataHolder(data, password)) {
                    startEncryption = false
                    if (it != null) {
                        onOutputReady(ReadyDataModel(data, it, password))
                    }
                    onDismissRequest()
                }
            }
        }
    }
}