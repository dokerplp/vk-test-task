package dokerplp.vktesttask.controller

import dokerplp.vktesttask.model.dto.AuthDto
import dokerplp.vktesttask.model.dto.GetFriendDto
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
class FriendsControllerTest(
    @Autowired private val userService: UserService,
    @Autowired private val userRepository: UserRepository,
    @Autowired private val authController: AuthController,
    @Autowired private val friendsController: FriendsController
) {

    @Test
    fun addFriend() {
        userService.findUserByLogin("test1") ?. let {  userRepository.removeByLogin("test1") }
        userService.findUserByLogin("test2") ?. let {  userRepository.removeByLogin("test2") }

        authController.signUp(GetRegisterDto(AuthDto("test1", "test"), "test", "test", Date()))
        authController.signUp(GetRegisterDto(AuthDto("test2", "test"), "test", "test", Date()))


        var u1 = userService.findUserByLoginAndPassword("test1", "test".toCharArray())
        var u2 = userService.findUserByLoginAndPassword("test2", "test".toCharArray())

        assert(u1 != null)
        assert(u2 != null)

        u1!!
        u2!!

        assert(u1.friends.size == 0)
        assert(u2.friends.size == 0)
        assert(u1.requests.size == 0)
        assert(u2.requests.size == 0)

        friendsController.addFriend(GetFriendDto(AuthDto("test1", "test"), "test2"))

        u1 = userService.findUserByLoginAndPassword("test1", "test".toCharArray())!!
        u2 = userService.findUserByLoginAndPassword("test2", "test".toCharArray())!!

        assert(u1.friends.size == 0)
        assert(u2.friends.size == 0)
        assert(u1.requests.size == 0)
        assert(u2.requests.size == 1)

        friendsController.addFriend(GetFriendDto(AuthDto("test2", "test"), "test1"))

        u1 = userService.findUserByLoginAndPassword("test1", "test".toCharArray())!!
        u2 = userService.findUserByLoginAndPassword("test2", "test".toCharArray())!!

        assert(u1.friends.size == 1)
        assert(u2.friends.size == 1)
        assert(u1.requests.size == 0)
        assert(u2.requests.size == 0)


        userService.findUserByLogin("test1") ?. let {  userRepository.removeByLogin("test1") }
        userService.findUserByLogin("test2") ?. let {  userRepository.removeByLogin("test2") }
    }

    @Test
    fun findFriend() {
        userService.findUserByLogin("test1") ?. let {  userRepository.removeByLogin("test1") }
        userService.findUserByLogin("test2") ?. let {  userRepository.removeByLogin("test2") }

        authController.signUp(GetRegisterDto(AuthDto("test1", "test"), "test", "test", Date()))

        assert( friendsController.findFriend(PostLoginDto("test1")) != null)
        assert( friendsController.findFriend(PostLoginDto("test2")) == null)

        userService.findUserByLogin("test1") ?. let {  userRepository.removeByLogin("test1") }
        userService.findUserByLogin("test2") ?. let {  userRepository.removeByLogin("test2") }
    }

    @Test
    fun deleteFriend() {

        userService.findUserByLogin("test1") ?. let {  userRepository.removeByLogin("test1") }
        userService.findUserByLogin("test2") ?. let {  userRepository.removeByLogin("test2") }

        authController.signUp(GetRegisterDto(AuthDto("test1", "test"), "test", "test", Date()))
        authController.signUp(GetRegisterDto(AuthDto("test2", "test"), "test", "test", Date()))

        var u1 = userService.findUserByLoginAndPassword("test1", "test".toCharArray())!!
        var u2 = userService.findUserByLoginAndPassword("test2", "test".toCharArray())!!

        assert(u1.friends.size == 0)
        assert(u2.friends.size == 0)
        assert(u1.requests.size == 0)
        assert(u2.requests.size == 0)

        friendsController.addFriend(GetFriendDto(AuthDto("test1", "test"), "test2"))
        friendsController.addFriend(GetFriendDto(AuthDto("test2", "test"), "test1"))

        u1 = userService.findUserByLoginAndPassword("test1", "test".toCharArray())!!
        u2 = userService.findUserByLoginAndPassword("test2", "test".toCharArray())!!

        assert(u1.friends.size == 1)
        assert(u2.friends.size == 1)
        assert(u1.requests.size == 0)
        assert(u2.requests.size == 0)

        friendsController.deleteFriend(GetFriendDto(AuthDto("test1", "test"), "test2"))

        u1 = userService.findUserByLoginAndPassword("test1", "test".toCharArray())!!
        u2 = userService.findUserByLoginAndPassword("test2", "test".toCharArray())!!

        assert(u1.friends.size == 0)
        assert(u2.friends.size == 0)
        assert(u1.requests.size == 0)
        assert(u2.requests.size == 0)

        userService.findUserByLogin("test1") ?. let {  userRepository.removeByLogin("test1") }
        userService.findUserByLogin("test2") ?. let {  userRepository.removeByLogin("test2") }
    }

    @Test
    fun cancelRequest() {
        userService.findUserByLogin("test1") ?. let {  userRepository.removeByLogin("test1") }
        userService.findUserByLogin("test2") ?. let {  userRepository.removeByLogin("test2") }

        authController.signUp(GetRegisterDto(AuthDto("test1", "test"), "test", "test", Date()))
        authController.signUp(GetRegisterDto(AuthDto("test2", "test"), "test", "test", Date()))

        var u1 = userService.findUserByLoginAndPassword("test1", "test".toCharArray())!!
        var u2 = userService.findUserByLoginAndPassword("test2", "test".toCharArray())!!

        assert(u1.friends.size == 0)
        assert(u2.friends.size == 0)
        assert(u1.requests.size == 0)
        assert(u2.requests.size == 0)

        friendsController.addFriend(GetFriendDto(AuthDto("test1", "test"), "test2"))
        u2 = userService.findUserByLoginAndPassword("test2", "test".toCharArray())!!

        assert(u1.friends.size == 0)
        assert(u2.friends.size == 0)
        assert(u1.requests.size == 0)
        assert(u2.requests.size == 1)

        friendsController.cancelRequest(GetFriendDto(AuthDto("test2", "test"), "test1"))
        u2 = userService.findUserByLoginAndPassword("test2", "test".toCharArray())!!

        assert(u1.friends.size == 0)
        assert(u2.friends.size == 0)
        assert(u1.requests.size == 0)
        assert(u2.requests.size == 0)

        userService.findUserByLogin("test1") ?. let {  userRepository.removeByLogin("test1") }
        userService.findUserByLogin("test2") ?. let {  userRepository.removeByLogin("test2") }
    }

    @Test
    fun friendsList() {
        userService.findUserByLogin("test1") ?. let {  userRepository.removeByLogin("test1") }
        userService.findUserByLogin("test2") ?. let {  userRepository.removeByLogin("test2") }
        userService.findUserByLogin("test3") ?. let {  userRepository.removeByLogin("test3") }

        authController.signUp(GetRegisterDto(AuthDto("test1", "test"), "test", "test", Date()))
        authController.signUp(GetRegisterDto(AuthDto("test2", "test"), "test", "test", Date()))
        authController.signUp(GetRegisterDto(AuthDto("test3", "test"), "test", "test", Date()))

        var u1 = userService.findUserByLoginAndPassword("test1", "test".toCharArray())!!
        var u2 = userService.findUserByLoginAndPassword("test2", "test".toCharArray())!!
        var u3 = userService.findUserByLoginAndPassword("test3", "test".toCharArray())!!

        assert(u1.friends.size == 0)
        assert(u1.requests.size == 0)

        u1.friends.add(u2)
        u1.friends.add(u3)

        userService.save(u1)
        u1 = userService.findUserByLoginAndPassword("test1", "test".toCharArray())!!

        assert(u1.friends.size == 2)

        userService.findUserByLogin("test1") ?. let {  userRepository.removeByLogin("test1") }
        userService.findUserByLogin("test2") ?. let {  userRepository.removeByLogin("test2") }
        userService.findUserByLogin("test3") ?. let {  userRepository.removeByLogin("test3") }
    }

    @Test
    fun requestsList() {

        userService.findUserByLogin("test1") ?. let {  userRepository.removeByLogin("test1") }
        userService.findUserByLogin("test2") ?. let {  userRepository.removeByLogin("test2") }
        userService.findUserByLogin("test3") ?. let {  userRepository.removeByLogin("test3") }

        authController.signUp(GetRegisterDto(AuthDto("test1", "test"), "test", "test", Date()))
        authController.signUp(GetRegisterDto(AuthDto("test2", "test"), "test", "test", Date()))
        authController.signUp(GetRegisterDto(AuthDto("test3", "test"), "test", "test", Date()))

        var u1 = userService.findUserByLoginAndPassword("test1", "test".toCharArray())!!
        var u2 = userService.findUserByLoginAndPassword("test2", "test".toCharArray())!!
        var u3 = userService.findUserByLoginAndPassword("test3", "test".toCharArray())!!

        assert(u1.requests.size == 0)
        assert(u1.requests.size == 0)

        u1.requests.add(u2)
        u1.requests.add(u3)

        userService.save(u1)
        u1 = userService.findUserByLoginAndPassword("test1", "test".toCharArray())!!

        assert(u1.requests.size == 2)

        userService.findUserByLogin("test1") ?. let {  userRepository.removeByLogin("test1") }
        userService.findUserByLogin("test2") ?. let {  userRepository.removeByLogin("test2") }
        userService.findUserByLogin("test3") ?. let {  userRepository.removeByLogin("test3") }
    }
}