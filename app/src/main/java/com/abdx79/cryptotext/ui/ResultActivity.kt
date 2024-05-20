package com.abdx79.cryptotext.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.abdx79.cryptotext.models.ReadyDataModel
import com.abdx79.cryptotext.ui.ui.theme.CryptoTextTheme
import kotlinx.serialization.json.Json

class ResultActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTextTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    CenterAlignedTopAppBar(title = { Text(text = "Result") })
                }) { innerPadding ->
                    val childModifier = Modifier.padding(innerPadding)
                    val resultData = Json.decodeFromString(intent.getStringExtra("result").toString()) as ReadyDataModel
                    ShowResultUi(childModifier, resultData)
                }
            }
        }
    }

    @Composable
    fun ShowResultUi(modifier: Modifier = Modifier, resultData: ReadyDataModel) {
        Column(modifier) {
            TextField(value = resultData.input, onValueChange = {})
            TextField(value = resultData.output, onValueChange = {})
            TextField(value = resultData.key, onValueChange = {})
        }
    }
}