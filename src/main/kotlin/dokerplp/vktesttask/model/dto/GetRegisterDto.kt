package dokerplp.vktesttask.model.dto

import java.util.Date

data class GetRegisterDto (
    val auth: AuthDto,
    val name: String,
    val surname: String,
    val birthday: Date
)