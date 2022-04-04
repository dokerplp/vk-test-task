package dokerplp.vktesttask.controller

import dokerplp.vktesttask.model.dto.GetFriendDto
import dokerplp.vktesttask.model.dto.GetMessageDto
import dokerplp.vktesttask.model.dto.PostMessageDto
import dokerplp.vktesttask.model.service.MessageService
import dokerplp.vktesttask.model.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import kotlin.streams.toList

/**
 * Rest controller for messages
 *
 * @property messageService - service for working with "messages database"
 * @property userService - executes methods with User entity
 */
@RestController
class MessageController(
    @Autowired private val messageService: MessageService,
    @Autowired private val userService: UserService
) {

    /**
     * Makes new "messages" database entry
     *
     * @param messageDto - auth data, time of sending, text and friend login
     * @return true if the system was able to save message
     */
    @PostMapping("/api/send-message")
    fun writeMessage(@RequestBody messageDto: GetMessageDto): Boolean {
        return messageService.save(messageDto)
    }

    /**
     * Returns all messages between user and friend
     *
     * @param friendDto - auth data and friend login
     * @return list of messages
     */
    @PostMapping("/api/get-messages")
    fun getMessages(@RequestBody friendDto: GetFriendDto): List<PostMessageDto> {
        val ids = userService.checkPair(friendDto.auth, friendDto.friend) ?: return ArrayList()
        return messageService.getAllByLogin(ids.first, ids.second)
            .stream()
            .map { m -> PostMessageDto(id = m.id, time = m.time, text = m.text, my = m.userid == ids.first) }
            .toList()
    }

}