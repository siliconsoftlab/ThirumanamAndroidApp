package com.smartherd.globofly.models

data class User(
    var id: Int = 0,
    var name: String? = null,
    var sex: String? = null,
    var password: String? = null,
    var incomingInterest: List<Interest>? =null,
    var outgoingInterest: List<Interest>? =null
)
