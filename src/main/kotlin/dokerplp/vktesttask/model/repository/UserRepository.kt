package dokerplp.vktesttask.model.repository

import dokerplp.vktesttask.model.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User,  Long> {
    fun findByLogin(login: String): User?
}