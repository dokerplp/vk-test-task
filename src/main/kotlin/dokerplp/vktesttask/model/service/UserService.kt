package dokerplp.vktesttask.model.service

import dokerplp.vktesttask.model.dto.AuthDto
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

    fun findUserByLogin(login: String): User? {
        return userRepository.findByLogin(login)
    }

    fun findUserByLoginAndPassword(login: String, password: CharArray): User? {
        val user = userRepository.findByLogin(login) ?: return null
        return if (passHashing.hash(user.salt, password).contentEquals(user.password)) user else null
    }

    fun save(user: User): Boolean {
        return try {
            userRepository.save(user)
            true
        } catch (e: Throwable) {
            false
        }
    }

    fun update(user: User) {
        userRepository.update(user.name, user.surname, user.birthday, user.login)
    }

    fun checkPair(authDto: AuthDto, login: String): Pair<Long, Long>? {
        val user = findUserByLoginAndPassword(authDto.login, authDto.pass.toCharArray())
            ?: return null
        val friend = findUserByLogin(login) ?: return null
        return Pair(user.id, friend.id)
    }

    fun getPair(authDto: AuthDto, login: String): Pair<User, User>? {
        val user = findUserByLoginAndPassword(authDto.login, authDto.pass.toCharArray()) ?: return null
        val friend = findUserByLogin(login) ?: return null
        return Pair(user, friend)
    }

}