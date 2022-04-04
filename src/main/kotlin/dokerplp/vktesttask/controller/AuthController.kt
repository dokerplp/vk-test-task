package dokerplp.vktesttask.controller

import dokerplp.vktesttask.model.dto.AuthDto
import dokerplp.vktesttask.model.dto.GetRegisterDto
import dokerplp.vktesttask.model.entity.User
import dokerplp.vktesttask.model.service.UserService
import dokerplp.vktesttask.security.PassHashing
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * Rest controller for login and register
 *
 * @property passHashing - make password secure
 * @property userService - executes methods with User entity
 */
@RestController
class AuthController(
    @Autowired private val passHashing: PassHashing,
    @Autowired private val userService: UserService
) {

    /**
     * Login method
     *
     * @param authDto - login / passwords information
     * @return true if the user exists, false otherwise
     */
    @PostMapping("/api/sign-in")
    fun signIn(@RequestBody authDto: AuthDto): Boolean {
        return userService.findUserByLoginAndPassword(authDto.login, authDto.pass.toCharArray()) != null
    }

    /**
     * Register method
     *
     * @param registerDto - auth information and register data
     * @return true if the system was able to create a user, false otherwise
     */
    @PostMapping("/api/sign-up")
    fun signUp(@RequestBody registerDto: GetRegisterDto): Boolean {
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