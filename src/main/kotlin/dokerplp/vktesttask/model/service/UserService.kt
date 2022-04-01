package dokerplp.vktesttask.model.service

import dokerplp.vktesttask.model.entity.User
import dokerplp.vktesttask.model.repository.UserRepository
import dokerplp.vktesttask.security.PassHashing
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserService(
    @Autowired private val passHashing: PassHashing,
    @Autowired private val userRepository: UserRepository
) {

    fun findUserByLoginAndPassword(login: String, password: CharArray): User? {
        val user = userRepository.findByLogin(login) ?: return null
        return if (passHashing.hash(user.salt, password).contentEquals(user.password)) user else null
    }

}