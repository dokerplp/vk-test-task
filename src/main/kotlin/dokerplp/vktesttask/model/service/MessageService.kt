package dokerplp.vktesttask.model.service

import dokerplp.vktesttask.model.dto.GetMessageDto
import dokerplp.vktesttask.model.entity.Message
import dokerplp.vktesttask.model.repository.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MessageService (
    @Autowired private val userService: UserService,
    @Autowired private val messageRepository: MessageRepository
) {
    fun save(message: Message) {
        messageRepository.save(message)
    }

    fun save(messageDto: GetMessageDto): Boolean {
        val ids = userService.checkPair(messageDto.auth, messageDto.friend) ?: return false
        save(Message(ids.first, ids.second, messageDto.text, messageDto.time))
        return true
    }

    fun getAllByLogin(user: Long, friend: Long): List<Message> {
        return messageRepository.findAllByUseridAndFriendid(user, friend)
    }
}