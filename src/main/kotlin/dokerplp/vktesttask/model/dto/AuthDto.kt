package dokerplp.vktesttask.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthDto (
    @JsonProperty("login") val login: String,
    @JsonProperty("pass") val pass: String
)