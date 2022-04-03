package dokerplp.vktesttask.controller

import dokerplp.vktesttask.model.dto.MessageDto
import dokerplp.vktesttask.model.service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(
    @Autowired private val messageService: MessageService
) {

    @PostMapping("/api/message")
    fun writeMessage(@RequestBody messageDto: MessageDto): Boolean {
        return messageService.save(messageDto)
    }

}