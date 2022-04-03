package dokerplp.vktesttask.model.dto

import java.util.*

data class PostUserDto (
    val login: String,
    val name: String,
    val surname: String,
    val birthday: Date
)