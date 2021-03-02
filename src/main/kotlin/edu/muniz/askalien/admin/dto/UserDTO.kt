package edu.muniz.askalien.admin.dto

data class UserDTO (
    val login: String,
    val password: String,
    var role: String? = null
)