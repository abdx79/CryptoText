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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.abdx79.cryptotext.AskPasswordDialog
import com.abdx79.cryptotext.models.ReadyDataModel
import com.abdx79.cryptotext.ui.ui.theme.CryptoTextTheme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class EncryptionActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var rawData: String
        setContent {
            CryptoTextTheme {
                Scaffold(Modifier.imePadding(), topBar = {
                    CenterAlignedTopAppBar(title = { Text("Encryption") })
                }, content = { innerPadding ->
                    val modifier = Modifier.padding(innerPadding)
//                    MakeEncUi(modifier)
                    MakeUi2(modifier)
                })
            }
        }
    }

    @Composable
    fun MakeEncUi(modifier: Modifier = Modifier, onEncrypt: (String) -> Unit = {}) {
        ConstraintLayout(modifier = modifier.fillMaxSize()) {
            val (button, text) = createRefs()
            var rawData: String by remember { mutableStateOf("") }
            OutlinedTextField(value = rawData, onValueChange = { rawData = it }, modifier = Modifier
                .fillMaxWidth()
                .constrainAs(text) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(button.top, 16.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                })

            Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(button) {
                start.linkTo(parent.start, 16.dp)
                end.linkTo(parent.end, 16.dp)
                bottom.linkTo(parent.bottom, margin = 16.dp)
                width = Dimension.fillToConstraints
            }) {
                Text("Encrypt")
            }
        }
    }


    @Composable
    fun MakeUi2(modifier: Modifier = Modifier) {
        var buttonClicked by remember { mutableStateOf(false) }
        var rawData: String by remember { mutableStateOf("") }
        var resultData: ReadyDataModel? by remember { mutableStateOf(null) }

        if (resultData != null) {
            LocalContext.current.startActivity(Intent(LocalContext.current, ResultActivity::class.java).apply {
                putExtra("result", Json.encodeToString(resultData))
            })
            resultData = null
        }


        if (buttonClicked) {
            AskPasswordDialog(rawData, onDismissRequest = { buttonClicked = false }) {
                resultData = it
            }
        }

        Column(
            modifier,
            verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(value = rawData,
                onValueChange = { rawData = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85F)
                    .padding(8.dp),
                label = { Text("Enter text here") })

            Button(onClick = {
                buttonClicked = true
            }, modifier = Modifier.fillMaxWidth(0.9F)) {
                Text("Encrypt")
            }

        }
    }
}
