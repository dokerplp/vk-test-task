package dokerplp.vktesttask.controller

import dokerplp.vktesttask.model.dto.AuthDto
import dokerplp.vktesttask.model.dto.GetRegisterDto
import dokerplp.vktesttask.model.entity.User
import dokerplp.vktesttask.model.repository.UserRepository
import dokerplp.vktesttask.model.service.UserService
import dokerplp.vktesttask.security.PassHashing
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Component
import java.util.*

@SpringBootTest
@Component
class AuthControllerTest(
    @Autowired private val authController: AuthController,
    @Autowired private val userService: UserService,
    @Autowired private val userRepository: UserRepository,
    @Autowired private val passHashing: PassHashing
) {

    @Test
    fun signIn() {
        userRepository.removeByLogin("test")
        userService.save(User("test", passHashing.hash("test".toByteArray(), "test".toCharArray()), "test".toByteArray(), "test", "test", Date()))

        assert(authController.signIn(AuthDto("test", "test")))
        userRepository.removeByLogin("test")
    }

    @Test
    fun signUp() {
        userRepository.removeByLogin("test")
        authController.signUp(GetRegisterDto(AuthDto("test", "test"), "test", "test", Date()))

        val user1 = userService.findUserByLogin("test")
        assert(user1 != null)

        val user2 = userService.findUserByLoginAndPassword("test", "test".toCharArray())
        assert(user2 != null)
        userRepository.removeByLogin("test")
    }
}