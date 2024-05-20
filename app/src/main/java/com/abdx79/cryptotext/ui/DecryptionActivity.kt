package com.abdx79.cryptotext.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.abdx79.cryptotext.PerformDecryption
import com.abdx79.cryptotext.models.ReadyDataModel
import com.abdx79.cryptotext.ui.ui.theme.CryptoTextTheme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DecryptionActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTextTheme {
                Scaffold(modifier = Modifier.imePadding(), topBar = {
                    CenterAlignedTopAppBar(title = { Text("Decryption") })
                }) { innerPadding ->
                    MakeUi(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MakeUi(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    var decryptClicked by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    var key by remember { mutableStateOf("") }



    if (decryptClicked) {
        PerformDecryption(data = text, key = key) {
            context.startActivity(Intent(context, ResultActivity::class.java).apply {
                putExtra("result", Json.encodeToString(it?.let { it1 -> ReadyDataModel(text, it1, key) }))
                decryptClicked = false
            })
        }
    }


    Column(
        modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter encrypted text") },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                value = key,
                onValueChange = { key = it },
                label = { Text("Enter key") },
                modifier = Modifier
                    .fillMaxWidth()

            )
            Button(
                onClick = {
                    decryptClicked = true
                }, modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Decrypt")
            }
        }
    }
}