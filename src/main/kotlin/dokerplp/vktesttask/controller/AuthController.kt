package dokerplp.vktesttask.controller

import dokerplp.vktesttask.model.dto.AuthDto
import dokerplp.vktesttask.model.service.UserService
import dokerplp.vktesttask.security.PassHashing
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    @Autowired private val userService: UserService
) {

    @PostMapping("/api/login")
    fun login(@RequestBody authDto: AuthDto): Boolean {
        return userService.findUserByLoginAndPassword(authDto.login, authDto.pass.toCharArray()) != null
    }



}