package dokerplp.vktesttask.model.dto

import java.util.*

data class PostMessageDto (
    val id: Long,
    val time: Date,
    val text: String,
    val my: Boolean
)