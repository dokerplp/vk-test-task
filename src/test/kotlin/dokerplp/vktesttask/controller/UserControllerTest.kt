package dokerplp.vktesttask.controller

import dokerplp.vktesttask.model.dto.AuthDto
import dokerplp.vktesttask.model.dto.GetRegisterDto
import dokerplp.vktesttask.model.dto.PostLoginDto
import dokerplp.vktesttask.model.repository.UserRepository
import dokerplp.vktesttask.model.service.UserService
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Component
import java.util.*

@SpringBootTest
@Component
class UserControllerTest(
    @Autowired private val userService: UserService,
    @Autowired private val userRepository: UserRepository,
    @Autowired private val userController: UserController,
    @Autowired private val authController: AuthController,
) {

    @Test
    fun getUser() {

        userService.findUserByLogin("test") ?. let {  userRepository.removeByLogin("test1") }
        authController.signUp(GetRegisterDto(AuthDto("test", "test"), "test", "test", Date()))

        var u1 = userService.findUserByLoginAndPassword("test", "test".toCharArray())!!

        val u2 = userController.getUser(PostLoginDto("test"))!!

        assert(u2.login == u1.login)
        assert(u2.name == u1.name)
        assert(u2.surname == u1.surname)
        assert(u2.birthday == u1.birthday)

        userService.findUserByLogin("test") ?. let {  userRepository.removeByLogin("test1") }
    }

    @Test
    fun changes() {

        userService.findUserByLogin("test") ?. let {  userRepository.removeByLogin("test1") }
        authController.signUp(GetRegisterDto(AuthDto("test", "test"), "test", "test", Date()))

        var u1 = userService.findUserByLoginAndPassword("test", "test".toCharArray())!!

        assert(u1.name != "test2")
        u1.name = "test2"

        assert(u1.surname != "test3")
        u1.surname = "test3"

        userService.save(u1)

        u1 =  userService.findUserByLoginAndPassword("test", "test".toCharArray())!!

        assert(u1.name == "test2")
        assert(u1.surname == "test3")

        userService.findUserByLogin("test") ?. let {  userRepository.removeByLogin("test1") }
    }
}