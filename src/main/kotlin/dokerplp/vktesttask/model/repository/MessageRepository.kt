package dokerplp.vktesttask.model.repository

import dokerplp.vktesttask.model.entity.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository: CrudRepository<Message, Long> {
    fun findAllByUseridAndFriendid(userid: Long, friendid: Long): List<Message>
}