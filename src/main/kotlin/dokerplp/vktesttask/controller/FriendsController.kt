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

@RestController
class FriendsController(
    @Autowired private val userService: UserService
) {

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

    @PostMapping("/api/find-friend")
    fun findFriend(@RequestBody loginDto: PostLoginDto): PostUserDto? {
        val user = userService.findUserByLogin(loginDto.login) ?: return null
        return PostUserDto(login = user.login, name = user.name, surname = user.surname, birthday = user.birthday)
    }

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

    @PostMapping("/api/cancel-friend")
    fun cancelRequest() {

    }

    @PostMapping("/api/friends-list")
    fun friendsList(@RequestBody authDto: AuthDto): List<PostUserDto> {
        return userService.findUserByLoginAndPassword(authDto.login, authDto.pass.toCharArray())!!.friends
            .stream().map { user -> PostUserDto(login = user.login, name = user.name, surname = user.surname, birthday = user.birthday) }
            .toList()
    }

    @PostMapping("/api/request-list")
    fun requestsList(@RequestBody authDto: AuthDto): List<PostUserDto> {
        return userService.findUserByLoginAndPassword(authDto.login, authDto.pass.toCharArray())!!.requests
            .stream().map { user -> PostUserDto(login = user.login, name = user.name, surname = user.surname, birthday = user.birthday) }
            .toList()
    }



}