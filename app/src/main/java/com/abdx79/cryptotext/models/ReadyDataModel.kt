package com.abdx79.cryptotext.models

import kotlinx.serialization.Serializable

@Serializable
data class ReadyDataModel(val input: String, val output: String, val key: String)
