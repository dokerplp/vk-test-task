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

@RestController
class MessageController(
    @Autowired private val messageService: MessageService,
    @Autowired private val userService: UserService
) {

    @PostMapping("/api/send-message")
    fun writeMessage(@RequestBody messageDto: GetMessageDto): Boolean {
        return messageService.save(messageDto)
    }

    @PostMapping("/api/get-messages")
    fun getMessages(@RequestBody friendDto: GetFriendDto): List<PostMessageDto> {
        val ids = userService.checkPair(friendDto.auth, friendDto.friend) ?: return ArrayList()
        return messageService.getAllByLogin(ids.first, ids.second)
            .stream()
            .map { m -> PostMessageDto(id = m.id, time = m.time, text = m.text, my = m.userid == ids.first) }
            .toList()
    }

}