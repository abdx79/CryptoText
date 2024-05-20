package com.abdx79.cryptotext

import android.util.Log
import java.security.spec.KeySpec
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

class CryptoLibrary {

    private val TAG = "CryptoLibrary"

    fun getConstantIv(): IvParameterSpec {
        return IvParameterSpec(ByteArray(16) { 0 })
    }

    private fun byteArrayToString(byteArray: ByteArray): String {
        return Base64.getEncoder().encodeToString(byteArray)
    }

    private fun stringToByteArray(string: String): ByteArray {
        return Base64.getDecoder().decode(string)
    }

    private fun generateKeyFromPassword(key: String): ByteArray {
        val iterationCount = 10000
        val keySpec: KeySpec = PBEKeySpec(key.toCharArray(), getConstantSalt(), iterationCount, 256)
        val secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        return secretKeyFactory.generateSecret(keySpec).encoded
    }

    private fun getConstantSalt(): ByteArray {
        return byteArrayOf(0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07)
    }

    fun encrypt(data: String, key: String, iv: IvParameterSpec): String {
        Log.i(TAG, "encrypt: data = $data, key = $key")
        val secretKey: SecretKey = SecretKeySpec(generateKeyFromPassword(key), "AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv)
        val encryptedBytes = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
        return byteArrayToString(encryptedBytes)
    }

    fun decrypt(data: String, key: String, iv: IvParameterSpec): String {
        Log.i(TAG, "decrypt: data = $data, key = $key")
        val secretKey: SecretKey = SecretKeySpec(generateKeyFromPassword(key), "AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv)
        val decryptedBytes = cipher.doFinal(stringToByteArray(data))
        return String(decryptedBytes, Charsets.UTF_8)
    }
}

fun main() {
    println("Bismillah!")
    val cryptoLibrary = CryptoLibrary()
    val key = "mySecretKey"
    val data = "Hello World!"
    println("Data: $data")
    val encryptedData = cryptoLibrary.encrypt(data, key, cryptoLibrary.getConstantIv())
    println("Encrypted Data: $encryptedData")
    val decryptedData = cryptoLibrary.decrypt(encryptedData, key, cryptoLibrary.getConstantIv())
    println("Decrypted Data: $decryptedData")
}
