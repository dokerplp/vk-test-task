package dokerplp.vktesttask.controller

import dokerplp.vktesttask.model.dto.AuthDto
import dokerplp.vktesttask.model.dto.GetFriendDto
import dokerplp.vktesttask.model.dto.PostLoginDto
import dokerplp.vktesttask.model.dto.PostUserDto
import dokerplp.vktesttask.model.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import kotlin.streams.toList

/**
 * Rest controller for friends communications
 *
 * @property userService - executes methods with User entity
 */
@RestController
class FriendsController(
    @Autowired private val userService: UserService
) {

    /**
     * Make new "requests" or "friends" database entry
     *
     * @param friendDto - auth data and friend login
     * @return true if the system was able to make database entry, false otherwise
     */
    @PostMapping("/api/add-friend")
    fun addFriend(@RequestBody friendDto: GetFriendDto): Boolean {
        val pair = userService.getPair(friendDto.auth, friendDto.friend) ?: return false
        val user = pair.first
        val friend = pair.second
        if (user.friends.find { it.login == friend.login } != null) return false
        user.requests.find { it.login == friend.login } ?. let {
            user.friends.add(friend)
            friend.friends.add(user)
            user.requests.removeIf { it.login == friend.login }
            friend.requests.removeIf { it.login == user.login }
        } ?: run {
            friend.requests.add(user)
        }
        userService.save(user)
        userService.save(friend)
        return true
    }

    /**
     * Finds user by login
     *
     * @param loginDto - user login
     * @return user data if system was able to find user, false otherwise
     */
    @PostMapping("/api/find-friend")
    fun findFriend(@RequestBody loginDto: PostLoginDto): PostUserDto? {
        val user = userService.findUserByLogin(loginDto.login) ?: return null
        return PostUserDto(login = user.login, name = user.name, surname = user.surname, birthday = user.birthday)
    }

    /**
     * Deletes "friends" database entry
     *
     * @param friendDto - auth data and friend login
     * @return true if system was able to delete entry, false otherwise
     */
    @PostMapping("/api/delete-friend")
    fun deleteFriend(@RequestBody friendDto: GetFriendDto): Boolean {
        val pair = userService.getPair(friendDto.auth, friendDto.friend) ?: return false
        val user = pair.first
        val friend = pair.second
        friend.friends.removeIf { it.login == user.login }
        user.friends.removeIf { it.login == friend.login }
        friend.requests.removeIf { it.login == user.login }
        userService.save(user)
        userService.save(friend)
        return true
    }

    /**
     * Deletes "requests" database entry
     *
     * @param friendDto - auth data and friend login
     * @return true if system was able to delete entry, false otherwise
     */
    @PostMapping("/api/cancel-friend")
    fun cancelRequest(@RequestBody friendDto: GetFriendDto): Boolean {
        val pair = userService.getPair(friendDto.auth, friendDto.friend) ?: return false
        val user = pair.first
        val friend = pair.second
        user.requests.removeIf { it.login == friend.login }
        userService.save(user)
        return true
    }

    /**
     * Returns user's friends
     *
     * @param authDto - user's auth data
     * @return list of users
     */
    @PostMapping("/api/friends-list")
    fun friendsList(@RequestBody authDto: AuthDto): List<PostUserDto> {
        return userService.findUserByLoginAndPassword(authDto.login, authDto.pass.toCharArray())!!.friends
            .stream().map { user -> PostUserDto(login = user.login, name = user.name, surname = user.surname, birthday = user.birthday) }
            .toList()
    }

    /**
     * Returns user's requests
     *
     * @param authDto - user's auth data
     * @return list of users
     */
    @PostMapping("/api/request-list")
    fun requestsList(@RequestBody authDto: AuthDto): List<PostUserDto> {
        return userService.findUserByLoginAndPassword(authDto.login, authDto.pass.toCharArray())!!.requests
            .stream().map { user -> PostUserDto(login = user.login, name = user.name, surname = user.surname, birthday = user.birthday) }
            .toList()
    }

}