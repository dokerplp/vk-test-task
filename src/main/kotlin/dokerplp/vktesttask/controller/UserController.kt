package dokerplp.vktesttask.controller

import dokerplp.vktesttask.model.dto.AuthDto
import dokerplp.vktesttask.model.dto.LoginDto
import dokerplp.vktesttask.model.dto.RegisterDto
import dokerplp.vktesttask.model.dto.UserDto
import dokerplp.vktesttask.model.entity.User
import dokerplp.vktesttask.model.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    @Autowired private val authController: AuthController,
    @Autowired private val userService: UserService
) {

    @PostMapping("/api/user")
    fun getUser(@RequestBody loginDto: LoginDto): UserDto? {
        val user = userService.findUserByLogin(loginDto.login) ?: return null
        return UserDto(user.name, user.surname, user.birthday)
    }

    @PostMapping("/api/user/change")
    fun changes(@RequestBody registerDto: RegisterDto) {
        if (authController.signIn(registerDto.auth)){
            val user = User()
            user.name = registerDto.name
            user.surname = registerDto.surname
            user.birthday = registerDto.birthday
            user.login = registerDto.auth.login
            userService.update(user)
        }
    }

}