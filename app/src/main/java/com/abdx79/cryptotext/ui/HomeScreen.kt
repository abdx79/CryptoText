package com.abdx79.cryptotext.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdx79.cryptotext.R


@Preview(widthDp = 360, heightDp = 640, showBackground = true)
@Composable
fun MakeHomeScreen() {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            MakeHomeScreenButtons(
                Modifier
                    .weight(1f)
                    .fillMaxHeight(0.5f)
            )
        }
    }
}

@Composable
fun RoundedElevatedButton(modifier: Modifier, onClick: () -> Unit, content: @Composable () -> Unit) {
    OutlinedButton(
        modifier = modifier.fillMaxWidth(),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 12.dp),
        shape = RoundedCornerShape(18.dp),
        contentPadding = PaddingValues(0.dp),
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        content()
    }
}

@Composable
fun MakeHomeScreenButtons(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    RoundedElevatedButton(modifier = modifier, onClick = {
        context.startActivity(Intent(context, EncryptionActivity::class.java))
    }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Image(
                modifier = Modifier.size(120.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.inversePrimary),
                painter = painterResource(id = R.drawable.enc),
                contentDescription = ""
            )
            Spacer(Modifier.padding(6.dp))
            Text(modifier = Modifier.align(Alignment.CenterHorizontally), text = "Encrypt", style = MaterialTheme.typography.titleLarge)
        }
    }

    Spacer(Modifier.padding(6.dp))

    RoundedElevatedButton(modifier = modifier, onClick = {
        context.startActivity(Intent(context, DecryptionActivity::class.java))
    }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Image(
                modifier = Modifier.size(120.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.inversePrimary),
                painter = painterResource(id = R.drawable.dec),
                contentDescription = ""
            )
            Spacer(Modifier.padding(6.dp))
            Text(modifier = Modifier.align(Alignment.CenterHorizontally), text = "Decrypt", style = MaterialTheme.typography.titleLarge)
        }
    }
}