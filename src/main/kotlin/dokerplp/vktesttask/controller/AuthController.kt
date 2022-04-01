package dokerplp.vktesttask.controller

import dokerplp.vktesttask.model.dto.AuthDto
import dokerplp.vktesttask.model.dto.RegisterDto
import dokerplp.vktesttask.model.entity.User
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
    @Autowired private val passHashing: PassHashing,
    @Autowired private val userService: UserService
) {

    @PostMapping("/api/sign-in")
    fun signIn(@RequestBody authDto: AuthDto): Boolean {
        return userService.findUserByLoginAndPassword(authDto.login, authDto.pass.toCharArray()) != null
    }

    @PostMapping("/api/sign-up")
    fun signUp(@RequestBody registerDto: RegisterDto): Boolean {
        val salt = passHashing.salt()
        val pass = passHashing.hash(salt, registerDto.auth.pass.toCharArray())
        return userService.save(
            User(
                login = registerDto.auth.login,
                password = pass,
                salt = salt,
                name = registerDto.name,
                surname = registerDto.surname,
                birthday = registerDto.birthday
            ))
    }

}