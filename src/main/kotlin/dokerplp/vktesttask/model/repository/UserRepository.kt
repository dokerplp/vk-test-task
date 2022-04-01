package dokerplp.vktesttask.model.repository

import dokerplp.vktesttask.model.entity.User
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.util.*

interface UserRepository : CrudRepository<User,  Long> {
    fun findByLogin(login: String): User?

    @Modifying
    @Transactional
    @Query("update User user set user.name = :name, user.surname = :surname, user.birthday = :birthday where user.login = :login")
    fun update(@Param("name") name: String, @Param("surname") surname: String, @Param("birthday") birthday: Date, @Param("login") login: String)
}