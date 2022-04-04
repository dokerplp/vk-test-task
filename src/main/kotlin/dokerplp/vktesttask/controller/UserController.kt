package dokerplp.vktesttask.controller

import dokerplp.vktesttask.model.dto.PostLoginDto
import dokerplp.vktesttask.model.dto.GetRegisterDto
import dokerplp.vktesttask.model.dto.PostUserDto
import dokerplp.vktesttask.model.entity.User
import dokerplp.vktesttask.model.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * Rest controller for user profile
 *
 * @property authController - for authentication
 * @property userService - executes methods with User entity
 */
@RestController
class UserController(
    @Autowired private val authController: AuthController,
    @Autowired private val userService: UserService
) {

    /**
     * Returns user by login
     *
     * @param loginDto - user login
     * @return user data
     */
    @PostMapping("/api/user")
    fun getUser(@RequestBody loginDto: PostLoginDto): PostUserDto? {
        val user = userService.findUserByLogin(loginDto.login) ?: return null
        return PostUserDto(user.login, user.name, user.surname, user.birthday)
    }

    /**
     * Updates user's data
     *
     * @param registerDto - new user's data
     */
    @PostMapping("/api/user/change")
    fun changes(@RequestBody registerDto: GetRegisterDto) {
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