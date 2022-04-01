package dokerplp.vktesttask.model.service

import dokerplp.vktesttask.model.entity.User
import dokerplp.vktesttask.model.repository.UserRepository
import dokerplp.vktesttask.security.PassHashing
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Arrays


@Service
class UserService(
    @Autowired private val passHashing: PassHashing,
    @Autowired private val userRepository: UserRepository
) {

    fun findUserByLoginAndPassword(login: String, password: CharArray): User? {
        val user = userRepository.findByLogin(login) ?: return null
        val p = passHashing.hash(user.salt, password)
        val pp = user.password
        return if (p.contentEquals(pp)) user else null
    }

    fun save(user: User): Boolean {
        return try {
            userRepository.save(user)
            true
        } catch (e: Throwable) {
            false
        }
    }

}