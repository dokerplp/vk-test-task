package dokerplp.vktesttask.model.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class User {
    @Id
    @Column(name = "id", nullable = false)
    var id: Long = 0

    @Column(name = "login", nullable = false)
    lateinit var login: String

    @Column(name = "password", nullable = false)
    lateinit var password: ByteArray

    @Column(name = "salt", nullable = false)
    lateinit var salt: ByteArray
}
