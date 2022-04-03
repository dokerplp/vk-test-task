package dokerplp.vktesttask.model.dto

import java.util.Date

data class MessageDto(
    val auth: AuthDto,
    val friend: String,
    val time: Date,
    val text: String
)