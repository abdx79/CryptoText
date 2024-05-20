package com.abdx79.cryptotext

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abdx79.cryptotext.models.CipherDataHolder


enum class MakeUiType {
    Encryption,
    Decryption
}

@Composable
fun PerformEncryption(modifier: Modifier = Modifier, dataHolder: CipherDataHolder, onFinish: (String?) -> Unit) {
    MakeProgressUi(dataHolder = dataHolder, makeUiType = MakeUiType.Encryption) {
        onFinish(it)
    }
}

@Composable
fun PerformDecryption(data: String, key: String, onFinish: (String?) -> Unit) {
    MakeProgressUi(dataHolder = CipherDataHolder(data, key), makeUiType = MakeUiType.Decryption) {
        onFinish(it)
    }
}

@Composable
fun MakeProgressUi(dataHolder: CipherDataHolder, makeUiType: MakeUiType, onFinish: (String?) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val cm = Modifier
        CircularProgressIndicator(modifier = cm)
        Text(text = "Encrypting", modifier = cm)
        LaunchedEffect(null) {
            CryptoLibrary().run {
                dataHolder.let {
                    run {
                        onFinish(
                            when (makeUiType) {
                                MakeUiType.Encryption -> encrypt(it.data, it.key, getConstantIv())
                                MakeUiType.Decryption -> decrypt(it.data, it.key, getConstantIv())
                            }
                        )
                    }
                }
            }
        }
    }
}