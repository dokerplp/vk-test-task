package dokerplp.vktesttask.controller

import dokerplp.vktesttask.model.dto.AuthDto
import dokerplp.vktesttask.model.dto.GetFriendDto
import dokerplp.vktesttask.model.dto.GetMessageDto
import dokerplp.vktesttask.model.dto.GetRegisterDto
import dokerplp.vktesttask.model.repository.MessageRepository
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
class MessageControllerTest(
    @Autowired private val userService: UserService,
    @Autowired private val userRepository: UserRepository,
    @Autowired private val authController: AuthController,
    @Autowired private val messageController: MessageController,
    @Autowired private val messageRepository: MessageRepository
) {

    @Test
    fun writeMessage() {
        userService.findUserByLogin("test1") ?. let {  userRepository.removeByLogin("test1") }
        userService.findUserByLogin("test2") ?. let {  userRepository.removeByLogin("test2") }

        authController.signUp(GetRegisterDto(AuthDto("test1", "test"), "test", "test", Date()))
        authController.signUp(GetRegisterDto(AuthDto("test2", "test"), "test", "test", Date()))

        var u1 = userService.findUserByLoginAndPassword("test1", "test".toCharArray())!!
        var u2 = userService.findUserByLoginAndPassword("test2", "test".toCharArray())!!

        messageController.writeMessage(GetMessageDto(AuthDto("test1", "test"), "test2", Date(), "text sample"))

        val test2msgs = messageRepository.findAllByUseridAndFriendid(u1.id, u2.id)

        val msg = test2msgs.find { it.friendid == u2.id }
        assert(msg != null)
        msg!!
        assert(msg.text == "text sample")

        userService.findUserByLogin("test1") ?. let {  userRepository.removeByLogin("test1") }
        userService.findUserByLogin("test2") ?. let {  userRepository.removeByLogin("test2") }
    }

    @Test
    fun getMessages() {

        userService.findUserByLogin("test1") ?. let {  userRepository.removeByLogin("test1") }
        userService.findUserByLogin("test2") ?. let {  userRepository.removeByLogin("test2") }

        authController.signUp(GetRegisterDto(AuthDto("test1", "test"), "test", "test", Date()))
        authController.signUp(GetRegisterDto(AuthDto("test2", "test"), "test", "test", Date()))

        var u1 = userService.findUserByLoginAndPassword("test1", "test".toCharArray())!!
        var u2 = userService.findUserByLoginAndPassword("test2", "test".toCharArray())!!

        messageController.writeMessage(GetMessageDto(AuthDto("test1", "test"), "test2", Date(), "text sample"))
        messageController.writeMessage(GetMessageDto(AuthDto("test1", "test"), "test2", Date(), "text sample"))
        messageController.writeMessage(GetMessageDto(AuthDto("test1", "test"), "test2", Date(), "text sample"))
        messageController.writeMessage(GetMessageDto(AuthDto("test1", "test"), "test2", Date(), "text sample"))
        messageController.writeMessage(GetMessageDto(AuthDto("test1", "test"), "test2", Date(), "text sample"))

        val test2msgs = messageController.getMessages(GetFriendDto(AuthDto("test2", "test"), "test1"))

        assert(test2msgs.size == 5)

        userService.findUserByLogin("test1") ?. let {  userRepository.removeByLogin("test1") }
        userService.findUserByLogin("test2") ?. let {  userRepository.removeByLogin("test2") }
    }
}